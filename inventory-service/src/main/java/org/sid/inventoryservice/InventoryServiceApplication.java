package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(ProductRepo productRepo){
		return args -> {
			Random random=new Random();
			for (int i = 0; i <10; i++) {
				productRepo.saveAll(List.of(
						Product.builder()
								.quantity(1+ random.nextInt(200))
								.price(2000+Math.random()*10000)
								.name("computer"+i).build()
				));
			}

		};
}
}
