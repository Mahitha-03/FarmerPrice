package com.agriprice.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agriprice.entity.CropPrice;
public interface CropPriceRepository extends JpaRepository<CropPrice,Long> {
 List<CropPrice> findByCropIdAndPriceDateOrderByPricePerKgDesc(Long cropId, LocalDate date);
 List<CropPrice> findByCropIdOrderByPriceDateAsc(Long cropId);
}
