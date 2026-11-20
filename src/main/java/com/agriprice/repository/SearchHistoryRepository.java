package com.agriprice.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.agriprice.entity.SearchHistory;
public interface SearchHistoryRepository extends JpaRepository<SearchHistory,Long> { List<SearchHistory> findTop20ByUserIdOrderBySearchedAtDesc(Long userId); }
