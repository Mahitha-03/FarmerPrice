package com.agriprice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agriprice.entity.Crop;
public interface CropRepository extends JpaRepository<Crop,Long> { List<Crop> findByActiveTrueOrderByNameAsc(); }
