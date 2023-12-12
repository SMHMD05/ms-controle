package org.sid.orderservice.repo;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {

}
