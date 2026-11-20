package com.agriprice.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;
public record ProfitRequest(@NotNull @Positive Long cropId, @NotNull @Positive BigDecimal quantityKg,
 @NotNull @PositiveOrZero BigDecimal transportExpense, @NotNull @PositiveOrZero BigDecimal otherExpense) {}
