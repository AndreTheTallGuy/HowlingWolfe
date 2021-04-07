package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
