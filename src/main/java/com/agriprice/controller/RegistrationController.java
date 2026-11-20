package com.agriprice.controller;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.agriprice.dto.RegistrationRequest;
import com.agriprice.service.UserService;

@Controller
public class RegistrationController {
 private final UserService service;
 public RegistrationController(UserService service){this.service=service;}
 @PostMapping("/register") public String create(@Valid @ModelAttribute RegistrationRequest request, BindingResult result, Model model){
  if(result.hasErrors()){model.addAttribute("error","Please check your details. Password must be at least 8 characters.");return "register";}
  try{service.register(request);return "redirect:/login?registered";}
  catch(IllegalArgumentException e){model.addAttribute("error",e.getMessage());return "register";}
 }
}
