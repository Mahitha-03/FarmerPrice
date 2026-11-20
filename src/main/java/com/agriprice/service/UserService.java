package com.agriprice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.agriprice.dto.RegistrationRequest;
import com.agriprice.entity.*;
import com.agriprice.repository.UserRepository;

@Service
public class UserService {
 private final UserRepository users; private final PasswordEncoder encoder;
 public UserService(UserRepository users, PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
 @Transactional public AppUser register(RegistrationRequest request){
  if(users.existsByEmail(request.email())) throw new IllegalArgumentException("An account with this email already exists.");
  AppUser u=new AppUser(); u.setFullName(request.fullName()); u.setEmail(request.email().toLowerCase().trim());
  u.setPassword(encoder.encode(request.password())); u.setPhone(request.phone()); u.setRole(Role.FARMER);
  return users.save(u);
 }
}
