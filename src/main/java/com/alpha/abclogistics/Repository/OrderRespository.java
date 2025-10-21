package com.alpha.abclogistics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.abclogistics.Entity.Order;

@Repository
public interface OrderRespository extends JpaRepository<Order, Integer> {
}

