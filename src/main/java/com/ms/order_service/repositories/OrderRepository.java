package com.ms.order_service.repositories;

import com.ms.order_service.domins.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
