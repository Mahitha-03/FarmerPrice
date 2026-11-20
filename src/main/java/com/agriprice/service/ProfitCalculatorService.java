package com.agriprice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import com.agriprice.dto.MarketProfit;

/** Pure business calculation: net = quantity * price - transport - other costs. */
@Service
public class ProfitCalculatorService {
    public MarketProfit calculate(Long marketId, String marketName, BigDecimal price,
            BigDecimal quantityKg, BigDecimal transport, BigDecimal other) {
        BigDecimal gross = price.multiply(quantityKg).setScale(2, RoundingMode.HALF_UP);
        BigDecimal net = gross.subtract(transport).subtract(other).setScale(2, RoundingMode.HALF_UP);
        return new MarketProfit(marketId, marketName, price, gross, transport, other, net);
    }
}
