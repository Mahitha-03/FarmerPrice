package com.agriprice.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,String> validation(MethodArgumentNotValidException ex){
  Map<String,String> errors=new LinkedHashMap<>();
  ex.getBindingResult().getFieldErrors().forEach(e->errors.put(e.getField(),e.getDefaultMessage()));return errors;
 }
 @ExceptionHandler(ResponseStatusException.class)
 public ResponseEntity<Map<String,String>> status(ResponseStatusException ex){
  return ResponseEntity.status(ex.getStatusCode()).body(Map.of("error",Objects.toString(ex.getReason(),"Request failed")));
 }
}
