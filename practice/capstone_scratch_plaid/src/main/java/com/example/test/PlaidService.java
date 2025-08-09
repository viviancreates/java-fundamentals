package com.example.test;

import com.plaid.client.ApiClient;
import com.plaid.client.model.CountryCode;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.ItemPublicTokenExchangeResponse;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.LinkTokenCreateResponse;
import com.plaid.client.model.Products;
import com.plaid.client.model.SandboxPublicTokenCreateRequest;
import com.plaid.client.model.SandboxPublicTokenCreateResponse;
import com.plaid.client.model.TransactionsGetRequest;
import com.plaid.client.model.TransactionsGetResponse;
import com.plaid.client.request.PlaidApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaidService {

    private static final Logger log = LoggerFactory.getLogger(PlaidService.class);

    @Value("${plaid.client-id}")
    private String clientId;

    @Value("${plaid.secret}")
    private String secret;

    private PlaidApi plaidClient;

    // In-memory storage
    private final List<Transaction> transactions = new ArrayList<>();
    private String currentAccessToken;

    public PlaidService() {
        // Initialize Plaid client after properties are injected
    }

    private void initPlaidClient() {
        try {
            HashMap<String, String> apiKeys = new HashMap<>();
            apiKeys.put("clientId", clientId);
            apiKeys.put("secret", secret);

            ApiClient apiClient = new ApiClient(apiKeys);
            apiClient.setPlaidAdapter(ApiClient.Sandbox);
            this.plaidClient = apiClient.createService(PlaidApi.class);

            log.info("✅ Plaid client initialized");
        } catch (Exception e) {
            log.error("❌ Failed to initialize Plaid client", e);
        }
    }

    public String createLinkToken() {
        if (plaidClient == null) {
            initPlaidClient();
        }

        try {
            LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                    .clientId(clientId)
                    .secret(secret)
                    .user(new LinkTokenCreateRequestUser().clientUserId("test_user_" + System.currentTimeMillis()))
                    .clientName("Plaid Test App")
                    .products(Arrays.asList(Products.TRANSACTIONS))
                    .countryCodes(Arrays.asList(CountryCode.US))
                    .language("en");

            Response<LinkTokenCreateResponse> response = plaidClient
                    .linkTokenCreate(request)
                    .execute();

            if (response.isSuccessful() && response.body() != null) {
                String linkToken = response.body().getLinkToken();
                log.info("✅ Created link token: {}...", linkToken.substring(0, Math.min(20, linkToken.length())));
                return linkToken;
            } else {
                String err = response.errorBody() != null ? response.errorBody().string() : "unknown error";
                log.error("❌ Failed to create link token: {}", err);
                return null;
            }
        } catch (Exception e) {
            log.error("❌ Error creating link token", e);
            return null;
        }
    }

    /** NEW: Create a real sandbox public_token so you can skip Plaid Link UI */
    public String createSandboxPublicToken() {
        if (plaidClient == null) {
            initPlaidClient();
        }

        try {
            SandboxPublicTokenCreateRequest req = new SandboxPublicTokenCreateRequest()
                    .institutionId("ins_109508") // First Platypus Bank (sandbox)
                    .initialProducts(Arrays.asList(Products.TRANSACTIONS));

            Response<SandboxPublicTokenCreateResponse> res =
                    plaidClient.sandboxPublicTokenCreate(req).execute();

            if (res.isSuccessful() && res.body() != null) {
                String publicToken = res.body().getPublicToken();
                log.info("✅ Sandbox public_token created: {}...", publicToken.substring(0, Math.min(20, publicToken.length())));
                return publicToken;
            } else {
                String err = res.errorBody() != null ? res.errorBody().string() : "unknown error";
                log.error("❌ sandboxPublicTokenCreate failed: {}", err);
                return null;
            }
        } catch (Exception e) {
            log.error("❌ Error creating sandbox public token", e);
            return null;
        }
    }

    public String exchangeToken(String publicToken) {
        if (plaidClient == null) {
            initPlaidClient();
        }

        try {
            ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
                    .clientId(clientId)
                    .secret(secret)
                    .publicToken(publicToken);

            Response<ItemPublicTokenExchangeResponse> response = plaidClient
                    .itemPublicTokenExchange(request)
                    .execute();

            if (response.isSuccessful() && response.body() != null) {
                this.currentAccessToken = response.body().getAccessToken();
                log.info("✅ Got access token: {}...", currentAccessToken.substring(0, Math.min(20, currentAccessToken.length())));
                return currentAccessToken;
            } else {
                String err = response.errorBody() != null ? response.errorBody().string() : "unknown error";
                log.error("❌ Failed to exchange token: {}", err);
                return null;
            }
        } catch (Exception e) {
            log.error("❌ Error exchanging token", e);
            return null;
        }
    }

    public List<Transaction> fetchTransactions() {
        if (currentAccessToken == null) {
            log.error("❌ No access token available. Run exchange token first.");
            return Collections.emptyList();
        }

        if (plaidClient == null) {
            initPlaidClient();
        }

        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(30);

            TransactionsGetRequest request = new TransactionsGetRequest()
                    .clientId(clientId)
                    .secret(secret)
                    .accessToken(currentAccessToken)
                    .startDate(startDate)
                    .endDate(endDate);

            Response<TransactionsGetResponse> response = plaidClient
                    .transactionsGet(request)
                    .execute();

            if (response.isSuccessful() && response.body() != null) {
                List<com.plaid.client.model.Transaction> plaidTransactions =
                        response.body().getTransactions();

                log.info("✅ Fetched {} transactions from Plaid", plaidTransactions.size());

                // Clear and populate in-memory storage
                transactions.clear();
                transactions.addAll(
                        plaidTransactions.stream()
                                .map(this::convertTransaction)
                                .collect(Collectors.toList())
                );

                log.info("📦 Stored {} transactions in memory", transactions.size());
                return new ArrayList<>(transactions);

            } else {
                String err = response.errorBody() != null ? response.errorBody().string() : "unknown error";
                log.error("❌ Failed to fetch transactions: {}", err);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("❌ Error fetching transactions", e);
            return Collections.emptyList();
        }
    }

    private Transaction convertTransaction(com.plaid.client.model.Transaction plaidTx) {
        String merchant = plaidTx.getMerchantName() != null
                ? plaidTx.getMerchantName()
                : "Unknown Merchant";

        String category = (plaidTx.getCategory() != null && !plaidTx.getCategory().isEmpty())
                ? plaidTx.getCategory().get(0)
                : "Other";

        String description = plaidTx.getName() != null ? plaidTx.getName() : "";
        LocalDate txDate = plaidTx.getDate();

        return new Transaction(
                plaidTx.getTransactionId(),
                merchant,
                category,
                BigDecimal.valueOf(plaidTx.getAmount()),
                txDate,
                description
        );
    }

    public List<Transaction> getStoredTransactions() {
        return new ArrayList<>(transactions);
    }

    public String getCurrentAccessToken() {
        return currentAccessToken;
    }

    public boolean hasAccessToken() {
        return currentAccessToken != null;
    }

    public int getTransactionCount() {
        return transactions.size();
    }
}

