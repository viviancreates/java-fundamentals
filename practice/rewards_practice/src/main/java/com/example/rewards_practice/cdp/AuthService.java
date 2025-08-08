package com.example.rewards_practice.cdp;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.EdECPrivateKey;
import java.security.spec.EdECPrivateKeySpec;
import java.security.spec.NamedParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;

@Component
public class AuthService {
    private final String apiKeyName;
    private final PrivateKey apiKeyEd25519;   // for "Authorization: Bearer <jwt>"
    private final PrivateKey walletP256;      // for "X-Wallet-Auth: <jwt>"

    private final String baseUrl;

    public AuthService() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        this.apiKeyName = mustGetEnv("API_KEY_NAME");              // e.g. ed88ae39-...
        String apiKeyPriv = mustGetEnv("API_KEY_PRIVATE_KEY");     // base64 seed OR PKCS#8 PEM
        String walletSecretPem = mustGetEnv("WALLET_SECRET");      // EC P-256 PEM

        this.baseUrl = System.getenv().getOrDefault("CDP_BASE_URL",
                "https://api.developer.coinbase.com");

        this.apiKeyEd25519 = loadEd25519PrivateKey(apiKeyPriv);
        this.walletP256    = loadP256PrivateKeyFromPem(walletSecretPem);
    }

    /* =======================================================================
       Public helpers used by CdpClient
       ======================================================================= */

    public String bearerFor(String httpMethod, String path) throws Exception {
        // Minimal JWT: iss = api key name, short-lived
        Instant now = Instant.now();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer(apiKeyName)
                .issueTime(java.util.Date.from(now))
                .expirationTime(java.util.Date.from(now.plusSeconds(90)))
                .claim("method", httpMethod)
                .claim("path", path)
                .claim("aud", baseUrl)
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.EdDSA)
                .type(JOSEObjectType.JWT)
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);
        JWSSigner signer = new Ed25519Signer((EdECPrivateKey) apiKeyEd25519);
        jwt.sign(signer);
        return jwt.serialize();
    }

    public String walletFor(String httpMethod, String path) throws Exception {
        // Wallet auth is ES256 (P-256)
        Instant now = Instant.now();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer("wallet")
                .issueTime(java.util.Date.from(now))
                .expirationTime(java.util.Date.from(now.plusSeconds(90)))
                .claim("method", httpMethod)
                .claim("path", path)
                .claim("aud", baseUrl)
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256)
                .type(JOSEObjectType.JWT)
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);
        JWSSigner signer = new ECDSASigner((ECPrivateKey) walletP256);
        jwt.sign(signer);
        return jwt.serialize();
    }

    /* =======================================================================
       Key loaders
       ======================================================================= */

    // Accepts either:
    //  - Base64 (or base64url) 32-byte Ed25519 seed, OR
    //  - PKCS#8 PEM ("-----BEGIN PRIVATE KEY-----")
    private static PrivateKey loadEd25519PrivateKey(String input) throws Exception {
        String trimmed = input.trim();

        if (trimmed.startsWith("-----BEGIN")) {
            // PKCS#8 PEM
            byte[] der = readPemToDer(trimmed);
            KeyFactory kf = KeyFactory.getInstance("Ed25519");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(der));
        }

        // Assume raw base64/base64url 32-byte seed
        byte[] seed;
        try {
            seed = Base64.getDecoder().decode(trimmed);
        } catch (IllegalArgumentException e) {
            seed = Base64.getUrlDecoder().decode(trimmed);
        }
        if (seed.length != 32) {
            throw new InvalidKeyException("Ed25519 seed must be 32 bytes; got " + seed.length);
        }

        KeyFactory kf = KeyFactory.getInstance("Ed25519");
        EdECPrivateKeySpec spec = new EdECPrivateKeySpec(NamedParameterSpec.ED25519, seed);
        return kf.generatePrivate(spec);
    }

    // Handles SEC1 EC ("BEGIN EC PRIVATE KEY") and PKCS#8 ("BEGIN PRIVATE KEY")
    private static PrivateKey loadP256PrivateKeyFromPem(String pem) throws Exception {
        try (PEMParser parser = new PEMParser(new StringReader(pem))) {
            Object o = parser.readObject();
            JcaPEMKeyConverter conv = new JcaPEMKeyConverter().setProvider("BC");

            PrivateKey key;
            if (o instanceof PEMKeyPair pemKeyPair) {
                key = conv.getKeyPair(pemKeyPair).getPrivate();
            } else if (o instanceof PrivateKeyInfo pki) {
                key = conv.getPrivateKey(pki);
            } else {
                // Some PEMs come in as plain PKCS#8 text—fallback by stripping armor
                byte[] der = readPemToDer(pem);
                KeyFactory kf = KeyFactory.getInstance("EC");
                key = kf.generatePrivate(new PKCS8EncodedKeySpec(der));
            }

            // Make sure it’s P-256
            if (!(key instanceof ECPrivateKey)) {
                throw new InvalidKeyException("Wallet key is not EC (P-256)");
            }
            return key;
        }
    }

    private static String mustGetEnv(String name) {
        String v = System.getenv(name);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing required env var: " + name);
        }
        return v;
    }

    private static byte[] readPemToDer(String pem) throws Exception {
        // Generic PEM unarmor
        String body = pem.replace("\r", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN EC PRIVATE KEY-----", "")
                .replace("-----END EC PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] der = Base64.getDecoder().decode(body);

        // Some libraries want a clean ASN.1 object — parse/serialize to normalize
        ASN1Primitive asn1 = ASN1Primitive.fromByteArray(der);
        return asn1.getEncoded();
    }
}
