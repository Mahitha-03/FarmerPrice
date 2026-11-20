package com.agriprice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.agriprice.repository.*;

@Controller
public class PageController {
 private final CropRepository crops; private final MarketRepository markets;
 public PageController(CropRepository crops,MarketRepository markets){this.crops=crops;this.markets=markets;}
 @GetMapping("/") public String home(){return "index";}
 @GetMapping("/login") public String login(){return "login";}
 @GetMapping("/register") public String register(){return "register";}
 @GetMapping("/dashboard") public String dashboard(Model model){model.addAttribute("crops",crops.findByActiveTrueOrderByNameAsc());model.addAttribute("markets",markets.findByActiveTrueOrderByNameAsc());return "dashboard";}
 @GetMapping("/admin") public String admin(Model model){model.addAttribute("crops",crops.findAll());model.addAttribute("markets",markets.findAll());return "admin";}
}
