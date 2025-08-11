package com.example.capstone_scratch.controller;

import com.example.capstone_scratch.model.TransactionRequest;
import com.example.capstone_scratch.TatumWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    @Autowired
    private TatumWalletService tatumWalletService;

    /**
     * GET /api/crypto/status - Health check
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getApiStatus() {
        Map<String, Object> status = Map.of(
                "status", "running",
                "provider", "Tatum API",
                "network", "Ethereum Sepolia Testnet",
                "timestamp", System.currentTimeMillis(),
                "message", "Crypto API is ready!"
        );
        return ResponseEntity.ok(status);
    }

    //POST /api/crypto/wallets -> Create new wallet

    @PostMapping("/wallets")
    public ResponseEntity<Map<String, Object>> createWallet() {
        Map<String, Object> result = tatumWalletService.createRealWallet();

        if ((Boolean) result.get("success")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    //GET /api/crypto/wallets/{address}/balance -> Get wallet balance

    @GetMapping("/wallets/{address}/balance")
    public ResponseEntity<Map<String, Object>> getWalletBalance(@PathVariable String address) {
        Map<String, Object> result = tatumWalletService.getRealWalletBalance(address);

        return result.containsKey("success") && (Boolean) result.get("success")
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    //POST /api/crypto/transactions -> Send transaction

    @PostMapping("/transactions")
    public ResponseEntity<Map<String, Object>> sendTransaction(@RequestBody TransactionRequest request) {
        try {
            // Validate input
            if (request.getFromAddress() == null || request.getToAddress() == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "error", "fromAddress and toAddress are required"
                ));
            }

            if (request.getAmount() == null || request.getAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "error", "amount must be greater than 0"
                ));
            }

            Map<String, Object> result = tatumWalletService.sendRealTransaction(
                    request.getFromAddress(),
                    request.getToAddress(),
                    request.getPrivateKey(),
                    request.getAmount()
            );

            if ((Boolean) result.get("success")) {
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
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

    //GET /api/crypto/wallets/{address}/transaction-readiness -> Check if wallet can send transactions
    @GetMapping("/wallets/{address}/transaction-readiness")
    public ResponseEntity<Map<String, Object>> checkTransactionReadiness(
            @PathVariable String address,
            @RequestParam(defaultValue = "0.001") BigDecimal amount) {

        Map<String, Object> result = tatumWalletService.checkTransactionReadiness(address, amount);

        return result.containsKey("success") && (Boolean) result.get("success")
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }


}