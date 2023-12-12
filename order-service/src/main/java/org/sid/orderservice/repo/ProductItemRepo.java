package org.sid.orderservice.repo;

import org.sid.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepo extends JpaRepository<ProductItem,Long> {

}
