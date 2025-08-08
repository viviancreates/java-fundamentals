package com.example.rewards_practice.api;

import com.example.rewards_practice.cdp.CdpClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WalletController {
    private final CdpClient cdp;

    public WalletController(CdpClient cdp) { this.cdp = cdp; }

    @PostMapping("/wallets")
    public Map<String, Object> createWallet() throws Exception {
        return cdp.createEvmAccount();
    }

    public record FaucetReq(String address) {}
    @PostMapping("/faucet")
    public Map<String, Object> faucet(@RequestBody FaucetReq req) throws Exception {
        return cdp.requestFaucet(req.address());
    }

    public record SendReq(String from, String to, String amountEth) {}
    @PostMapping("/tx/send")
    public Map<String, Object> send(@RequestBody SendReq req) throws Exception {
        BigInteger wei = new BigDecimal(req.amountEth())
                .multiply(new BigDecimal("1000000000000000000")) // 1e18
                .toBigInteger();
        return cdp.sendEth(req.from(), req.to(), wei.toString());
    }
}
