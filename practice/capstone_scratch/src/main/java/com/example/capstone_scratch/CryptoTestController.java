package com.example.capstone_scratch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class CryptoTestController {

    // In-memory storage - no database needed
    private final Map<String, Map<String, BigDecimal>> wallets = new HashMap<>();
    private final List<Map<String, Object>> transactions = new ArrayList<>();
    private static Long walletCounter = 1L;
    private static Long transactionCounter = 1L;

    /**
     * ACTION 1: Create a wallet
     * POST /api/test/create-wallet
     * Body: {"userId": "user123"}
     */
    @PostMapping("/create-wallet")
    public ResponseEntity<Map<String, Object>> createWallet(@RequestBody Map<String, String> request) {
        try {
            String userId = request.get("userId");
            String walletId = "wallet_" + walletCounter++;

            // Initialize wallet with zero balances
            Map<String, BigDecimal> balances = new HashMap<>();
            balances.put("ETH", BigDecimal.ZERO);
            balances.put("BTC", BigDecimal.ZERO);
            balances.put("USDC", BigDecimal.ZERO);

            wallets.put(walletId, balances);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("walletId", walletId);
            response.put("userId", userId);
            response.put("message", "Wallet created successfully");
            response.put("initialBalances", balances);

            System.out.println("✅ Created wallet: " + walletId + " for user: " + userId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = Map.of(
                    "success", false,
                    "error", e.getMessage()
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * ACTION 2: Fund wallet (simulate crypto reward deposit)
     * POST /api/test/fund-wallet
     * Body: {"walletId": "wallet_1", "coinType": "ETH", "amount": "0.001"}
     */
    @PostMapping("/fund-wallet")
    public ResponseEntity<Map<String, Object>> fundWallet(@RequestBody Map<String, String> request) {
        try {
            String walletId = request.get("walletId");
            String coinType = request.get("coinType");
            BigDecimal amount = new BigDecimal(request.get("amount"));

            // Check if wallet exists
            if (!wallets.containsKey(walletId)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "error", "Wallet not found: " + walletId
                ));
            }

            // Add funds to wallet
            Map<String, BigDecimal> wallet = wallets.get(walletId);
            BigDecimal currentBalance = wallet.get(coinType);
            BigDecimal newBalance = currentBalance.add(amount);
            wallet.put(coinType, newBalance);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("walletId", walletId);
            response.put("coinType", coinType);
            response.put("amountAdded", amount);
            response.put("newBalance", newBalance);
            response.put("message", "Wallet funded successfully");

            System.out.println("💰 Funded wallet: " + walletId + " with " + amount + " " + coinType);
            System.out.println("    New balance: " + newBalance + " " + coinType);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = Map.of(
                    "success", false,
                    "error", e.getMessage()
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * ACTION 3: Send transaction (simulate purchase + reward calculation)
     * POST /api/test/send-transaction
     * Body: {"walletId": "wallet_1", "merchant": "Starbucks", "amount": "25.50", "category": "RESTAURANT"}
     */
    @PostMapping("/send-transaction")
    public ResponseEntity<Map<String, Object>> sendTransaction(@RequestBody Map<String, String> request) {
        try {
            String walletId = request.get("walletId");
            String merchant = request.get("merchant");
            BigDecimal purchaseAmount = new BigDecimal(request.get("amount"));
            String category = request.get("category");

            // Check if wallet exists
            if (!wallets.containsKey(walletId)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "error", "Wallet not found: " + walletId
                ));
            }

            // Calculate rewards based on category
            Map<String, BigDecimal> rewardRates = Map.of(
                    "GROCERY", new BigDecimal("0.05"),     // 5%
                    "GAS", new BigDecimal("0.03"),         // 3%
                    "RESTAURANT", new BigDecimal("0.02"),  // 2%
                    "OTHER", new BigDecimal("0.01")        // 1%
            );

            BigDecimal rewardRate = rewardRates.getOrDefault(category, new BigDecimal("0.01"));
            BigDecimal totalRewardUSD = purchaseAmount.multiply(rewardRate);

            // Simulate crypto prices
            Map<String, BigDecimal> prices = Map.of(
                    "ETH", new BigDecimal("2500.00"),
                    "BTC", new BigDecimal("45000.00"),
                    "USDC", new BigDecimal("1.00")
            );

            // Split rewards (40% ETH, 40% BTC, 20% USDC)
            BigDecimal ethRewardUSD = totalRewardUSD.multiply(new BigDecimal("0.40"));
            BigDecimal btcRewardUSD = totalRewardUSD.multiply(new BigDecimal("0.40"));
            BigDecimal usdcRewardUSD = totalRewardUSD.multiply(new BigDecimal("0.20"));

            // Convert USD to crypto amounts
            BigDecimal ethAmount = ethRewardUSD.divide(prices.get("ETH"), 8, RoundingMode.HALF_UP);
            BigDecimal btcAmount = btcRewardUSD.divide(prices.get("BTC"), 8, RoundingMode.HALF_UP);
            BigDecimal usdcAmount = usdcRewardUSD; // USDC = $1

            // Add rewards to wallet
            Map<String, BigDecimal> wallet = wallets.get(walletId);
            wallet.put("ETH", wallet.get("ETH").add(ethAmount));
            wallet.put("BTC", wallet.get("BTC").add(btcAmount));
            wallet.put("USDC", wallet.get("USDC").add(usdcAmount));

            // Create transaction record
            Map<String, Object> transaction = new HashMap<>();
            transaction.put("id", transactionCounter++);
            transaction.put("walletId", walletId);
            transaction.put("merchant", merchant);
            transaction.put("category", category);
            transaction.put("purchaseAmount", purchaseAmount);
            transaction.put("rewardRate", rewardRate);
            transaction.put("totalRewardUSD", totalRewardUSD);
            transaction.put("rewards", Map.of(
                    "ETH", ethAmount,
                    "BTC", btcAmount,
                    "USDC", usdcAmount
            ));
            transaction.put("timestamp", System.currentTimeMillis());

            transactions.add(transaction);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("transaction", transaction);
            response.put("newWalletBalances", wallet);
            response.put("message", "Transaction processed and rewards awarded");

            System.out.println("🛒 Transaction: $" + purchaseAmount + " at " + merchant);
            System.out.println("🎁 Rewards: " + ethAmount + " ETH, " + btcAmount + " BTC, " + usdcAmount + " USDC");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = Map.of(
                    "success", false,
                    "error", e.getMessage()
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * BONUS: Get wallet status
     * GET /api/test/wallet/{walletId}
     */
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<Map<String, Object>> getWallet(@PathVariable String walletId) {
        if (!wallets.containsKey(walletId)) {
            return ResponseEntity.notFound().build();
        }

        Map<String, BigDecimal> balances = wallets.get(walletId);

        // Calculate total value
        BigDecimal totalValue = balances.get("ETH").multiply(new BigDecimal("2500"))
                .add(balances.get("BTC").multiply(new BigDecimal("45000")))
                .add(balances.get("USDC"));

        Map<String, Object> response = Map.of(
                "walletId", walletId,
                "balances", balances,
                "totalValueUSD", totalValue,
                "prices", Map.of(
                        "ETH", "2500.00",
                        "BTC", "45000.00",
                        "USDC", "1.00"
                )
        );

        return ResponseEntity.ok(response);
    }

    /**
     * BONUS: Get all transactions
     * GET /api/test/transactions
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Map<String, Object>>> getAllTransactions() {
        return ResponseEntity.ok(transactions);
    }

    /**
     * BONUS: Reset everything (for testing)
     * DELETE /api/test/reset
     */
    @DeleteMapping("/reset")
    public ResponseEntity<Map<String, Object>> reset() {
        wallets.clear();
        transactions.clear();
        walletCounter = 1L;
        transactionCounter = 1L;

        System.out.println("🔄 Reset all data");

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "All data reset"
        ));
    }
}