package com.example.capstone_scratch_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/real")
@CrossOrigin(origins = "*")
public class RealCryptoController {

    @Autowired
    private TatumWalletService tatumWalletService;

    /**
     * Check if API is working
     * GET /api/real/status
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getApiStatus() {
        return ResponseEntity.ok(Map.of(
                "status", "Real Crypto API is running",
                "provider", "Tatum API",
                "network", "Ethereum Sepolia Testnet",
                "message", "Ready to create real wallets!"
        ));
    }

    /**
     * REAL ACTION 1: Create actual wallet on Ethereum testnet
     * POST /api/real/create-wallet
     */
    @PostMapping("/create-wallet")
    public ResponseEntity<Map<String, Object>> createRealWallet() {
        Map<String, Object> result = tatumWalletService.createRealWallet();

        if ((Boolean) result.get("success")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * REAL ACTION 2: Get actual balance from blockchain
     * GET /api/real/balance/{walletAddress}
     */
    @GetMapping("/balance/{walletAddress}")
    public ResponseEntity<Map<String, Object>> getRealBalance(@PathVariable String walletAddress) {
        Map<String, Object> result = tatumWalletService.getRealWalletBalance(walletAddress);

        if ((Boolean) result.get("success")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * REAL ACTION 3: Send actual blockchain transaction
     * POST /api/real/send-transaction
     * Body: {
     *   "fromAddress": "0x...",
     *   "toAddress": "0x...",
     *   "privateKey": "0x...",
     *   "amount": "0.001"
     * }
     */
    @PostMapping("/send-transaction")
    public ResponseEntity<Map<String, Object>> sendRealTransaction(@RequestBody Map<String, String> request) {
        try {
            String fromAddress = request.get("fromAddress");
            String toAddress = request.get("toAddress");
            String privateKey = request.get("privateKey");
            BigDecimal amount = new BigDecimal(request.get("amount"));

            Map<String, Object> result = tatumWalletService.sendRealTransaction(
                    fromAddress, toAddress, privateKey, amount
            );

            if ((Boolean) result.get("success")) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", e.getMessage()
            ));
        }
    }
}