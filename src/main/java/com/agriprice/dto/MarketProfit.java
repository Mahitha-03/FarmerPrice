package com.agriprice.dto;
import java.math.BigDecimal;
public record MarketProfit(Long marketId, String marketName, BigDecimal pricePerKg,
 BigDecimal grossRevenue, BigDecimal transportExpense, BigDecimal otherExpense, BigDecimal netEarnings) {}
