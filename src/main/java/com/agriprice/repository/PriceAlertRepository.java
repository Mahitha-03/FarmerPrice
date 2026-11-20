package com.agriprice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agriprice.entity.PriceAlert;
public interface PriceAlertRepository extends JpaRepository<PriceAlert,Long> { List<PriceAlert> findByUserIdOrderByCreatedAtDesc(Long userId); }
