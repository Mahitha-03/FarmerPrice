package com.agriprice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="users", indexes=@Index(name="idx_users_role", columnList="role"))
public class AppUser extends BaseEntity {
    @NotBlank @Size(max=80) private String fullName;
    @Email @NotBlank @Column(nullable=false, unique=true, length=120) private String email;
    @NotBlank @Column(nullable=false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private Role role = Role.FARMER;
    @Size(max=20) private String phone;
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public Role getRole(){return role;} public void setRole(Role v){role=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
}
