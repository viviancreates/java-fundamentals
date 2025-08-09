package com.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private PlaidService plaidService;

    @GetMapping("/")
    public String home() {
        return """
            <h2>🏦 Plaid Test API</h2>
            <h3>Test Flow:</h3>
            <ol>
                <li><a href="/test/link-token">GET /test/link-token</a> - Create link token</li>
                <li><strong>OR</strong> <form action="/test/sandbox-public-token" method="post" style="display:inline;"><button>POST /test/sandbox-public-token</button></form> - Get sandbox public token (no Link)</li>
                <li><strong>POST /test/exchange</strong> - Exchange public token</li>
                <li><a href="/test/transactions">GET /test/transactions</a> - Fetch transactions</li>
                <li><a href="/test/stored">GET /test/stored</a> - View stored data</li>
            </ol>
            <hr>
            <p><strong>Current Status:</strong> %d transactions in memory | Access Token: %s</p>
            """.formatted(
                plaidService.getTransactionCount(),
                plaidService.hasAccessToken() ? "✅ Available" : "❌ Missing"
        );
    }

    @GetMapping("/link-token")
    public ResponseEntity<Map<String, Object>> createLinkToken() {
        log.info("🔗 Creating link token...");
        String token = plaidService.createLinkToken();

        if (token != null) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "link_token", token,
                    "instructions", "Use this token with Plaid Link Demo at https://plaid.com/docs/link/demo/",
                    "credentials", "user_good / pass_good"
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", "Failed to create link token"
            ));
        }
    }

    /** NEW: issue a real sandbox public_token so FE can skip Link */
    @PostMapping("/sandbox-public-token")
    public ResponseEntity<Map<String, Object>> sandboxPublicToken() {
        log.info("🧪 Creating sandbox public token...");
        String publicToken = plaidService.createSandboxPublicToken();
        if (publicToken != null) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "public_token", publicToken
            ));
        }
        return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", "Could not create sandbox public token"
        ));
    }

    @PostMapping("/exchange")
    public ResponseEntity<Map<String, Object>> exchangeToken(@RequestBody Map<String, String> body) {
        String publicToken = body.get("public_token");

        if (publicToken == null || publicToken.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", "public_token is required in request body"
            ));
        }

        log.info("🔄 Exchanging public token...");
        String accessToken = plaidService.exchangeToken(publicToken);

        if (accessToken != null) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Token exchanged successfully! Now you can fetch transactions.",
                    "access_token_preview", accessToken.substring(0, 20) + "..."
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", "Failed to exchange token"
            ));
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, Object>> fetchTransactions() {
        if (!plaidService.hasAccessToken()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", "No access token available. Run /test/exchange first."
            ));
        }

        log.info("📄 Fetching transactions from Plaid...");
        List<Transaction> transactions = plaidService.fetchTransactions();

        return ResponseEntity.ok(Map.of(
                "success", true,
                "count", transactions.size(),
                "transactions", transactions,
                "message", "Transactions fetched and stored in memory"
        ));
    }

    @GetMapping("/stored")
    public ResponseEntity<Map<String, Object>> getStoredTransactions() {
        List<Transaction> transactions = plaidService.getStoredTransactions();

        return ResponseEntity.ok(Map.of(
                "count", transactions.size(),
                "transactions", transactions,
                "has_access_token", plaidService.hasAccessToken(),
                "status", transactions.isEmpty() ? "No transactions stored" : "Transactions available"
        ));
    }

    @GetMapping("/clear")
    public ResponseEntity<Map<String, Object>> clearData() {
        log.info("🗑️ Clearing stored data...");
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Data cleared (access token still valid)"
        ));
    }
}
