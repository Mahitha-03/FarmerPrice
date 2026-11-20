package com.agriprice.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.agriprice.entity.*;
import com.agriprice.repository.*;

/** Adds clearly labelled demo data only when tables are empty. Replace with verified mandi data before production. */
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seed(CropRepository crops, MarketRepository markets, CropPriceRepository prices,
   UserRepository users, PasswordEncoder encoder,
   @org.springframework.beans.factory.annotation.Value("${APP_ADMIN_EMAIL:admin@agriprice.local}") String adminEmail,
   @org.springframework.beans.factory.annotation.Value("${APP_ADMIN_PASSWORD:ChangeThisAdminPassword123!}") String adminPassword){
  return args->{
   if(!users.existsByEmail(adminEmail)){AppUser a=new AppUser();a.setFullName("AgriPrice Admin");a.setEmail(adminEmail);a.setPassword(encoder.encode(adminPassword));a.setRole(Role.ADMIN);users.save(a);}
   Crop tomato=crops.findAll().stream().filter(c->c.getName().equalsIgnoreCase("Tomato")).findFirst().orElseGet(()->{Crop c=new Crop();c.setName("Tomato");c.setCategory("Vegetable");c.setUnit("kg");c.setDescription("Demo crop record");return crops.save(c);});
   Crop chilli=crops.findAll().stream().filter(c->c.getName().equalsIgnoreCase("Green Chilli")).findFirst().orElseGet(()->{Crop c=new Crop();c.setName("Green Chilli");c.setCategory("Vegetable");c.setUnit("kg");return crops.save(c);});
   Market m1=market(markets,"Uppal Demo Market","Medchal-Malkajgiri","Telangana");
   Market m2=market(markets,"Warangal Demo Market","Warangal","Telangana");
   if(prices.count()==0){ add(prices,tomato,m1,"20.00");add(prices,tomato,m2,"22.00");add(prices,chilli,m1,"35.00");add(prices,chilli,m2,"32.00");}
  };
 }
 private Market market(MarketRepository r,String n,String d,String s){return r.findAll().stream().filter(m->m.getName().equals(n)).findFirst().orElseGet(()->{Market m=new Market();m.setName(n);m.setDistrict(d);m.setState(s);m.setAddress("Sample location - verify before travel");return r.save(m);});}
 private void add(CropPriceRepository r,Crop c,Market m,String v){CropPrice p=new CropPrice();p.setCrop(c);p.setMarket(m);p.setPricePerKg(new BigDecimal(v));p.setPriceDate(LocalDate.now());p.setSource("DEMO - NOT A LIVE MARKET QUOTE");r.save(p);}
}
