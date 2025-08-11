package com.example.capstone_scratch;

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

    //ACTION 1: Create a real wallet using Tatum API
    public Map<String, Object> createRealWallet() {
        try {
            System.out.println("Creating real wallet via Tatum API...");

            HttpHeaders headers = createHeaders();

            // Create Ethereum wallet on testnet
            String url = TATUM_BASE_URL + "/ethereum/wallet";

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            System.out.println("Tatum API Response: " + response.getBody());
            JsonNode walletData = objectMapper.readTree(response.getBody());

            // Extract the xpub from wallet creation
            String xpub = walletData.path("xpub").asText();

            // Now generate the first wallet address from the xpub
            String walletAddress = generateWalletAddress(xpub, 0); // Index 0 = first address

            // Generate the private key for the first address
            String privateKey = generatePrivateKey(walletData.path("mnemonic").asText(), 0);

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("xpub", xpub);
            result.put("mnemonic", walletData.path("mnemonic").asText());
            result.put("walletAddress", walletAddress);
            result.put("privateKey", privateKey);
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("provider", "Tatum API");
            result.put("note", "Wallet address and private key generated for index 0");

            System.out.println("Real wallet created with address: " + walletAddress);

            return result;

        } catch (Exception e) {
            System.err.println("Failed to create real wallet: " + e.getMessage());
            e.printStackTrace();
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    // Generate wallet address from xpub
    private String generateWalletAddress(String xpub, int index) {
        try {
            System.out.println("generating wallet address from xpub at index: " + index);

            HttpHeaders headers = createHeaders();

            // Generate address from xpub using Tatum API
            String url = TATUM_BASE_URL + "/ethereum/address/" + xpub + "/" + index;

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JsonNode addressData = objectMapper.readTree(response.getBody());
            String address = addressData.path("address").asText();

            System.out.println("Generated wallet address: " + address);

            return address;

        } catch (Exception e) {
            System.err.println("Failed to generate wallet address: " + e.getMessage());
            e.printStackTrace();
            return "0x0000000000000000000000000000000000000000"; // Fallback
        }
    }

    //Generate private key from mnemonic
    private String generatePrivateKey(String mnemonic, int index) {
        try {
            System.out.println("Generating private key from mnemonic at index: " + index);

            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Generate private key from mnemonic using Tatum API
            String url = TATUM_BASE_URL + "/ethereum/wallet/priv";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("index", index);
            requestBody.put("mnemonic", mnemonic);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            JsonNode keyData = objectMapper.readTree(response.getBody());
            String privateKey = keyData.path("key").asText();

            System.out.println("Generated private key: " + privateKey.substring(0, 10) + "...");

            return privateKey;

        } catch (Exception e) {
            System.err.println("Failed to generate private key: " + e.getMessage());
            e.printStackTrace();
            return "0x00000"; //as a fallback
        }
    }

    /**
     * ACTION 2: Get real wallet balance using Tatum API
     */
    public Map<String, Object> getRealWalletBalance(String walletAddress) {
        try {
            System.out.println("Fetching real balance for: " + walletAddress);

            HttpHeaders headers = createHeaders();

            // Get ETH balance on Sepolia testnet
            String url = TATUM_BASE_URL + "/ethereum/account/balance/" + walletAddress;

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            System.out.println("Tatum balance API response: " + response.getBody());

            JsonNode balanceData = objectMapper.readTree(response.getBody());
            String balanceString = balanceData.path("balance").asText();

            // Convert Wei to ETH for display
            BigDecimal balanceInWei = new BigDecimal(balanceString);
            BigDecimal balanceInEth = balanceInWei.divide(new BigDecimal("1000000000000000000")); // 1 ETH = 10^18 Wei

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("walletAddress", walletAddress);
            result.put("ethBalance", balanceInEth);
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("balanceInWei", balanceString);

            System.out.println("Real balance fetched: " + balanceInEth + " ETH");

            return result;

        } catch (Exception e) {
            System.err.println("Failed to get real balance: " + e.getMessage());
            e.printStackTrace();
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    //ACTION 3: Send real transaction using Tatum API

    public Map<String, Object> sendRealTransaction(String fromAddress, String toAddress,
                                                   String privateKey, BigDecimal amount) {
        try {
            System.out.println("Sending real transaction via Tatum API...");
            System.out.println("From: " + fromAddress);
            System.out.println("To: " + toAddress);
            System.out.println("Amount: " + amount + " ETH");

            HttpHeaders headers = createHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Prepare transaction data in CORRECT Tatum format
            Map<String, Object> transactionData = new HashMap<>();
            transactionData.put("fromPrivateKey", privateKey);
            transactionData.put("to", toAddress);
            transactionData.put("amount", amount.toString());
            transactionData.put("currency", "ETH");

            // Fee as object
            Map<String, String> feeObject = new HashMap<>();
            feeObject.put("gasLimit", "21000");
            feeObject.put("gasPrice", "20");
            transactionData.put("fee", feeObject);

            System.out.println("🔍 Transaction data: " + transactionData);

            String url = TATUM_BASE_URL + "/ethereum/transaction";

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(transactionData, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            System.out.println("🔍 Tatum transaction response: " + response.getBody());
            JsonNode txData = objectMapper.readTree(response.getBody());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("transactionHash", txData.path("txId").asText());
            result.put("fromAddress", fromAddress);
            result.put("toAddress", toAddress);
            result.put("amount", amount);
            result.put("network", "Ethereum Sepolia Testnet");
            result.put("status", "Pending confirmation");
            result.put("gasLimit", "21000");
            result.put("gasPrice", "20 Gwei");

            System.out.println("Real transaction sent! Hash: " + txData.path("txId").asText());

            return result;

        } catch (Exception e) {
            System.err.println("Failed to send real transaction: " + e.getMessage());
            e.printStackTrace();
            return Map.of("success", false, "error", e.getMessage());
        }
    }


    //ACTION 4: Check if wallet has sufficient funds for transaction

    public Map<String, Object> checkTransactionReadiness(String walletAddress, BigDecimal amount) {
        try {
            System.out.println("Checking transaction readiness for: " + walletAddress);

            // Get current balance
            Map<String, Object> balanceResult = getRealWalletBalance(walletAddress);

            if (!(Boolean) balanceResult.get("success")) {
                return balanceResult;
            }

            BigDecimal currentBalance = (BigDecimal) balanceResult.get("ethBalance");
            BigDecimal requiredAmount = amount.add(new BigDecimal("0.002")); // Amount + estimated gas fees

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("walletAddress", walletAddress);
            result.put("currentBalance", currentBalance);
            result.put("requestedAmount", amount);
            result.put("estimatedGasFee", "0.002 ETH");
            result.put("totalRequired", requiredAmount);
            result.put("readyForTransaction", currentBalance.compareTo(requiredAmount) >= 0);

            if (currentBalance.compareTo(requiredAmount) >= 0) {
                result.put("status", "Ready for transaction");
            } else {
                result.put("status", "Insufficient funds");
                result.put("fundsNeeded", requiredAmount.subtract(currentBalance));
            }

            System.out.println("Transaction readiness check complete");

            return result;

        } catch (Exception e) {
            System.err.println("Failed to check transaction readiness: " + e.getMessage());
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