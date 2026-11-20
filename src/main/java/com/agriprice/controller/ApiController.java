package com.agriprice.controller;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.agriprice.dto.*;
import com.agriprice.entity.*;
import com.agriprice.repository.*;
import com.agriprice.service.MarketComparisonService;

@RestController
@RequestMapping("/api")
public class ApiController {
 private final CropRepository crops; private final MarketRepository markets; private final CropPriceRepository prices;
 private final MarketComparisonService comparison;
 public ApiController(CropRepository c,MarketRepository m,CropPriceRepository p,MarketComparisonService s){crops=c;markets=m;prices=p;comparison=s;}
 @GetMapping("/public/crops") public List<Crop> crops(){return crops.findByActiveTrueOrderByNameAsc();}
 @GetMapping("/public/markets") public List<Market> markets(){return markets.findByActiveTrueOrderByNameAsc();}
 @GetMapping("/public/crops/{id}/prices") public List<CropPrice> prices(@PathVariable Long id,@RequestParam(defaultValue="") String date){
  if(!crops.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Crop not found");
  LocalDate d=date.isBlank()?LocalDate.now():LocalDate.parse(date);
  return prices.findByCropIdAndPriceDateOrderByPricePerKgDesc(id,d);
 }
 @PostMapping("/profit/compare") public List<MarketProfit> compare(@Valid @RequestBody ProfitRequest request){
  if(!crops.existsById(request.cropId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Crop not found");
  return comparison.compare(request);
 }
 @PostMapping("/admin/crops") @ResponseStatus(HttpStatus.CREATED) public Crop addCrop(@Valid @RequestBody Crop crop){return crops.save(crop);}
 @PutMapping("/admin/crops/{id}") public Crop updateCrop(@PathVariable Long id,@Valid @RequestBody Crop input){
  Crop c=crops.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
  c.setName(input.getName());c.setCategory(input.getCategory());c.setUnit(input.getUnit());c.setDescription(input.getDescription());c.setActive(input.isActive());return crops.save(c);
 }
 @DeleteMapping("/admin/crops/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteCrop(@PathVariable Long id){
  Crop c=crops.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));c.setActive(false);crops.save(c);
 }
 @PostMapping("/admin/markets") @ResponseStatus(HttpStatus.CREATED) public Market addMarket(@Valid @RequestBody Market market){return markets.save(market);}
 @PostMapping("/admin/prices") @ResponseStatus(HttpStatus.CREATED) public CropPrice addPrice(@RequestParam Long cropId,@RequestParam Long marketId,@RequestParam String price,@RequestParam String date){
  CropPrice p=new CropPrice();p.setCrop(crops.findById(cropId).orElseThrow());p.setMarket(markets.findById(marketId).orElseThrow());
  p.setPricePerKg(new java.math.BigDecimal(price));p.setPriceDate(LocalDate.parse(date));p.setSource("ADMIN ENTERED");return prices.save(p);
 }
}
