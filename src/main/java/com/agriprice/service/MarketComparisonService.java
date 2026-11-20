package com.agriprice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Service;
import com.agriprice.dto.*;
import com.agriprice.repository.CropPriceRepository;

@Service
public class MarketComparisonService {
 private final CropPriceRepository prices; private final ProfitCalculatorService calculator;
 public MarketComparisonService(CropPriceRepository prices, ProfitCalculatorService calculator){this.prices=prices;this.calculator=calculator;}
 public List<MarketProfit> compare(ProfitRequest request){
  return prices.findByCropIdAndPriceDateOrderByPricePerKgDesc(request.cropId(), LocalDate.now()).stream()
   .map(p->calculator.calculate(p.getMarket().getId(),p.getMarket().getName(),p.getPricePerKg(),
       request.quantityKg(),request.transportExpense(),request.otherExpense()))
   .sorted(Comparator.comparing(MarketProfit::netEarnings).reversed()).toList();
 }
}
