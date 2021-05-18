package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o JOIN o.boats b WHERE b.date >= CURRENT_DATE")
    Set<Order> findUpcoming();

    @Query("SELECT o FROM Order o JOIN o.boats b WHERE b.date = CURRENT_DATE")
    Set<Order> getTodaysOrders();

    @Query("SELECT o FROM Order o JOIN o.boats b WHERE b.date = ?1")
    Set<Order> findByDate(Date date);
}
