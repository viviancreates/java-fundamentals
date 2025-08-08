package com.example.rewards_practice.cdp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class CdpClient {

    private final WebClient http;

    public CdpClient(
            @Value("${cdp.baseUrl}") String baseUrl,
            @Value("${cdp.token}") String bearerToken
    ) {
        this.http = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /** Create an EVM account (wallet) */
    public Map<String, Object> createEvmAccount() {
        return http.post()
                .uri("/v2/evm/accounts")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /** Request faucet funds on Base Sepolia */
    public Map<String, Object> requestFaucet(String address) {
        return http.post()
                .uri("/v2/evm/faucet")
                .bodyValue(Map.of(
                        "address", address,
                        "network", "base-sepolia",
                        "token", "eth"
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /** Send a tiny ETH tx from account/address to recipient (value in wei) */
    public Map<String, Object> sendEth(String from, String to, String valueWei) {
        var payload = Map.of(
                "address", from,
                "network", "base-sepolia",
                "transaction", Map.of("to", to, "value", valueWei)
        );
        return http.post()
                .uri("/v2/evm/transactions")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    /** Read balances (optional) */
    public Map<String, Object> getBalances(String accountId) {
        return http.get()
                .uri("/v2/evm/accounts/{id}/balances", accountId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}