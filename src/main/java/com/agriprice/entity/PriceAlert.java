package com.agriprice.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="price_alerts")
public class PriceAlert extends BaseEntity {
    @ManyToOne(optional=false) @JoinColumn(name="user_id",nullable=false) private AppUser user;
    @ManyToOne(optional=false) @JoinColumn(name="crop_id",nullable=false) private Crop crop;
    @NotNull @DecimalMin("0.01") @Column(nullable=false,precision=12,scale=2) private BigDecimal targetPrice;
    private boolean active = true;
    public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
    public Crop getCrop(){return crop;} public void setCrop(Crop v){crop=v;}
    public BigDecimal getTargetPrice(){return targetPrice;} public void setTargetPrice(BigDecimal v){targetPrice=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
