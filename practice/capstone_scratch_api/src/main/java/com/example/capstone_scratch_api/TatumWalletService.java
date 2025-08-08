package com.example.capstone_scratch_api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TatumWalletService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Use your testnet API key here
    private final String TATUM_API_KEY = "t-68965eb3c60a24089e2e3e7d-36b1ddd74bf4450c8aa95069";
    private final String TATUM_BASE_URL = "https://api.tatum.io/v3";

    /**
     * ACTION 1: Create a real wallet using Tatum API
     */
    public Map<String, Object> createRealWallet() {
        try {
            System.out.println("🔄 Creating real wallet via Tatum API...");

            HttpHeaders headers = createHeaders();

            // Create Ethereum wallet on testnet
            String url = TATUM_BASE_URL + "/ethereum/wallet";

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            System.out.println("🔍 Tatum API Response: " + response.getBody());
            JsonNode walletData = objectMapper.readTree(response.getBody());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("xpub", walletData.path("xpub").asText());          // This is what Tatum returns
            result.put("mnemonic", walletData.path("mnemonic").asText());
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("provider", "Tatum API");
            result.put("note", "Use xpub to derive wallet addresses");

            System.out.println("✅ Real wallet created: " + walletData.path("address").asText());

            return result;

        } catch (Exception e) {
            System.err.println("❌ Failed to create real wallet: " + e.getMessage());
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    /**
     * ACTION 2: Get real wallet balance using Tatum API
     */
    public Map<String, Object> getRealWalletBalance(String walletAddress) {
        try {
            System.out.println("🔄 Fetching real balance for: " + walletAddress);

            HttpHeaders headers = createHeaders();

            // Get ETH balance on Sepolia testnet
            String url = TATUM_BASE_URL + "/ethereum/account/balance/" + walletAddress;

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JsonNode balanceData = objectMapper.readTree(response.getBody());
            BigDecimal ethBalance = new BigDecimal(balanceData.path("balance").asText());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("walletAddress", walletAddress);
            result.put("ethBalance", ethBalance);
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("balanceInWei", balanceData.path("balance").asText());

            System.out.println("✅ Real balance fetched: " + ethBalance + " ETH");

            return result;

        } catch (Exception e) {
            System.err.println("❌ Failed to get real balance: " + e.getMessage());
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    /**
     * ACTION 3: Send real transaction using Tatum API
     */
    public Map<String, Object> sendRealTransaction(String fromAddress, String toAddress,
                                                   String privateKey, BigDecimal amount) {
        try {
            System.out.println("🔄 Sending real transaction via Tatum API...");

            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Prepare transaction data
            Map<String, Object> transactionData = new HashMap<>();
            transactionData.put("fromAddress", fromAddress);
            transactionData.put("to", toAddress);
            transactionData.put("amount", amount.toString());
            transactionData.put("privateKey", privateKey);
            transactionData.put("fee", "0.001"); // Gas fee

            String url = TATUM_BASE_URL + "/ethereum/transaction";

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(transactionData, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            JsonNode txData = objectMapper.readTree(response.getBody());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("transactionHash", txData.path("txId").asText());
            result.put("fromAddress", fromAddress);
            result.put("toAddress", toAddress);
            result.put("amount", amount);
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("status", "Pending confirmation");

            System.out.println("✅ Real transaction sent! Hash: " + txData.path("txId").asText());

            return result;

        } catch (Exception e) {
            System.err.println("❌ Failed to send real transaction: " + e.getMessage());
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", TATUM_API_KEY);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}