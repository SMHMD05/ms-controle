package org.sid.orderservice;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repo.OrderRepo;
import org.sid.orderservice.repo.ProductItemRepo;
import org.sid.orderservice.service.CustomerRestClientService;
import org.sid.orderservice.service.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(OrderRepo orderRepo, ProductItemRepo productItemRepo, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers=customerRestClientService.Allcustomers().getContent().stream().toList();
			List<Product> products=inventoryRestClientService.Allproducts().getContent().stream().toList();
			Long customerId=1l;
			Customer customer=customerRestClientService.customerById(customerId);
			Random random=new Random();
			for (int i = 0; i <20 ; i++) {
				Order order= Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5? OrderStatus.PENDING:OrderStatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder= orderRepo.save(order);
				for (int j = 0; j < products.size() ; j++) {
					if (Math.random()>0.70){
						ProductItem productItem= ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quatity(1+random.nextInt(10))
								.discount(Math.random())
								.build();
						productItemRepo.save(productItem);
					}

					
				}
			}
		};
	}

}