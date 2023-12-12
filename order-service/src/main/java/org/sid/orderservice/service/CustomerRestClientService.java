package org.sid.orderservice.service;

import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClientService {
    @GetMapping("/customers/{id}?projection=fullcustomer")
    public Customer customerById(@PathVariable Long id);
    @GetMapping("/customers?projection=fullcustomer")
    public PagedModel<Customer> Allcustomers();

}
