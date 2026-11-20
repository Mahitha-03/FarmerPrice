package com.agriprice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="search_history", indexes=@Index(name="idx_search_user_date",columnList="user_id,searched_at"))
public class SearchHistory extends BaseEntity {
    @ManyToOne(optional=false) @JoinColumn(name="user_id",nullable=false) private AppUser user;
    @ManyToOne(optional=false) @JoinColumn(name="crop_id",nullable=false) private Crop crop;
    @Column(nullable=false) private LocalDateTime searchedAt;
    @PrePersist void setSearchTime(){ if(searchedAt==null) searchedAt=LocalDateTime.now(); }
    public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
    public Crop getCrop(){return crop;} public void setCrop(Crop v){crop=v;}
    public LocalDateTime getSearchedAt(){return searchedAt;}
}
