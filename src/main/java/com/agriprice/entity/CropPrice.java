package com.agriprice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="crop_prices",
 uniqueConstraints=@UniqueConstraint(name="uk_price_crop_market_date", columnNames={"crop_id","market_id","price_date"}),
 indexes={@Index(name="idx_price_crop_date",columnList="crop_id,price_date"),@Index(name="idx_price_market",columnList="market_id")})
public class CropPrice extends BaseEntity {
    @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="crop_id", nullable=false) private Crop crop;
    @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="market_id", nullable=false) private Market market;
    @NotNull @DecimalMin("0.01") @Digits(integer=10,fraction=2) @Column(nullable=false, precision=12,scale=2) private BigDecimal pricePerKg;
    @NotNull @Column(name="price_date",nullable=false) private LocalDate priceDate;
    @Size(max=120) private String source = "DEMO DATA";
    public Crop getCrop(){return crop;} public void setCrop(Crop v){crop=v;}
    public Market getMarket(){return market;} public void setMarket(Market v){market=v;}
    public BigDecimal getPricePerKg(){return pricePerKg;} public void setPricePerKg(BigDecimal v){pricePerKg=v;}
    public LocalDate getPriceDate(){return priceDate;} public void setPriceDate(LocalDate v){priceDate=v;}
    public String getSource(){return source;} public void setSource(String v){source=v;}
}
