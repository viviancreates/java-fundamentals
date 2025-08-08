package com.example.rewards_practice.cdp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class CdpClient {
    private final WebClient http;
    private final AuthService auth;

    public CdpClient(
            AuthService auth,
            @Value("${CDP_BASE_URL:https://api.developer.coinbase.com}") String baseUrl
    ) {
        this.auth = auth;
        this.http = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public Map<String, Object> createEvmAccount() throws Exception {
        String path = "/v2/evm/accounts";
        String bearer = auth.bearerFor("POST", path);
        String wallet = auth.walletFor("POST", path);

        return http.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + bearer)
                .header("X-Wallet-Auth", wallet)
                .exchangeToMono(res -> {
                    if (res.statusCode().is2xxSuccessful()) {
                        return res.bodyToMono(Map.class);
                    }
                    return res.bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .flatMap(body -> Mono.error(new RuntimeException(
                                    "CDP /accounts failed: " + res.statusCode() + " " + body)));
                })
                .timeout(Duration.ofSeconds(20))
                .block();
    }

    public Map<String, Object> requestFaucet(String address) throws Exception {
        String path = "/v2/evm/faucet";
        String bearer = auth.bearerFor("POST", path);
        String wallet = auth.walletFor("POST", path);

        var body = Map.of(
                "address", address,
                "network", "base-sepolia",
                "token", "eth"
        );

        return http.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + bearer)
                .header("X-Wallet-Auth", wallet)
                .bodyValue(body)
                .exchangeToMono(res -> {
                    if (res.statusCode().is2xxSuccessful()) return res.bodyToMono(Map.class);
                    return res.bodyToMono(String.class).defaultIfEmpty("")
                            .flatMap(b -> Mono.error(new RuntimeException(
                                    "CDP /faucet failed: " + res.statusCode() + " " + b)));
                })
                .timeout(Duration.ofSeconds(20))
                .block();
    }

    public Map<String, Object> sendEth(String from, String to, String valueWei) throws Exception {
        String path = "/v2/evm/transactions";
        String bearer = auth.bearerFor("POST", path);
        String wallet = auth.walletFor("POST", path);

        var body = Map.of(
                "address", from,
                "network", "base-sepolia",
                "transaction", Map.of("to", to, "value", valueWei)
        );

        return http.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + bearer)
                .header("X-Wallet-Auth", wallet)
                .bodyValue(body)
                .exchangeToMono(res -> {
                    if (res.statusCode().is2xxSuccessful()) return res.bodyToMono(Map.class);
                    return res.bodyToMono(String.class).defaultIfEmpty("")
                            .flatMap(b -> Mono.error(new RuntimeException(
                                    "CDP /transactions failed: " + res.statusCode() + " " + b)));
                })
                .timeout(Duration.ofSeconds(30))
                .block();
    }
}
