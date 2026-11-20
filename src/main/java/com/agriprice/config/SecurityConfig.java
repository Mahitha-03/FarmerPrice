package com.agriprice.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import com.agriprice.repository.UserRepository;

@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository users){
  return email -> users.findByEmail(email).map(u->User.withUsername(u.getEmail()).password(u.getPassword())
   .authorities(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())).build())
   .orElseThrow(()->new UsernameNotFoundException("User not found"));
 }
 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http.authorizeHttpRequests(auth->auth.requestMatchers("/","/register","/css/**","/js/**","/api/public/**","/error").permitAll()
   .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
   .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll())
   .logout(logout->logout.logoutSuccessUrl("/").permitAll());
  return http.build();
 }
}
