package com.agriprice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="markets", indexes=@Index(name="idx_market_district", columnList="district"))
public class Market extends BaseEntity {
    @NotBlank @Size(max=120) @Column(nullable=false, unique=true) private String name;
    @NotBlank @Size(max=80) private String district;
    @NotBlank @Size(max=80) private String state;
    @Size(max=255) private String address;
    private boolean active = true;
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDistrict(){return district;} public void setDistrict(String v){district=v;}
    public String getState(){return state;} public void setState(String v){state=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
