package com.idfinance.task.repository;

import com.idfinance.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users JOIN prices ON users.price_id=prices.id " +
            "JOIN currencies ON prices.currency_id=currencies.id WHERE currencies.id=?", nativeQuery = true)
    List<User> findUserByCurrency(Long id);
}
