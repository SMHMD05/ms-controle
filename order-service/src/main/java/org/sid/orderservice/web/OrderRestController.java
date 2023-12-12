package org.sid.orderservice.web;

import lombok.AllArgsConstructor;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repo.OrderRepo;
import org.sid.orderservice.repo.ProductItemRepo;
import org.sid.orderservice.service.CustomerRestClientService;
import org.sid.orderservice.service.InventoryRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {
    private OrderRepo orderRepo;
    private ProductItemRepo productItemRepo;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;
    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
      Order order=orderRepo.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
