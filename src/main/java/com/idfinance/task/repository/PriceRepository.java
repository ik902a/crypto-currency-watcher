package com.idfinance.task.repository;

import com.idfinance.task.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(
            value = "SELECT * FROM prices WHERE currency_id = ? ORDER BY updated DESC LIMIT 1",
            nativeQuery = true)
    Optional<Price> findActualPrice(Long id);
}
