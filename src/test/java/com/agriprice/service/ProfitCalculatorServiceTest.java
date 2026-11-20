package com.agriprice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProfitCalculatorServiceTest {
 @Test void calculatesNetEarnings() {
  var result = new ProfitCalculatorService().calculate(1L,"Demo Market",new BigDecimal("20.00"),
   new BigDecimal("1000"),new BigDecimal("3000"),new BigDecimal("500"));
  assertEquals(new BigDecimal("20000.00"),result.grossRevenue());
  assertEquals(new BigDecimal("16500.00"),result.netEarnings());
 }
}
