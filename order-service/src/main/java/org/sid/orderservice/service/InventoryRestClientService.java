package org.sid.orderservice.service;

import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface    InventoryRestClientService {
    @GetMapping("/products/{id}?projection=fullproduct")
    public Product productById(@PathVariable Long id);
    @GetMapping("/products?projection=fullproduct")
    public PagedModel<Product> Allproducts();

}
