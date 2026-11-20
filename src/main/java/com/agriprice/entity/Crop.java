package com.agriprice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="crops", indexes=@Index(name="idx_crops_name", columnList="name"))
public class Crop extends BaseEntity {
    @NotBlank @Size(max=100) @Column(nullable=false, unique=true) private String name;
    @Size(max=60) private String category;
    @Size(max=20) private String unit = "kg";
    @Column(length=500) private String description;
    private boolean active = true;
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getUnit(){return unit;} public void setUnit(String v){unit=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
