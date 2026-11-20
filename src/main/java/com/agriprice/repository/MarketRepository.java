package com.agriprice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agriprice.entity.Market;
public interface MarketRepository extends JpaRepository<Market,Long> { List<Market> findByActiveTrueOrderByNameAsc(); }
