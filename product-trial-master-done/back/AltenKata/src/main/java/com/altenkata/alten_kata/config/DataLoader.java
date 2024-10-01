package com.altenkata.alten_kata.config;

import com.altenkata.alten_kata.domain.Product;
import com.altenkata.alten_kata.model.InventoryStatus;
import com.altenkata.alten_kata.repos.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Preload products if not already present
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setCode("P001");
            product1.setName("Product 1");
            product1.setDescription("Description for Product 1");
            product1.setImage("image1.png");
            product1.setCategory("Category1");
            product1.setPrice(10.0);
            product1.setQuantity(100.0);
            product1.setInternalReference("INT-001");
            product1.setShellId(1L);
            product1.setRating(4.5);
            product1.setInventoryStatus(InventoryStatus.INSTOCK);
            product1.setDateCreated(LocalDateTime.now());
            product1.setLastUpdated(LocalDateTime.now());

            Product product2 = new Product();
            product2.setCode("P002");
            product2.setName("Product 2");
            product2.setDescription("Description for Product 2");
            product2.setImage("image2.png");
            product2.setCategory("Category2");
            product2.setPrice(15.0);
            product2.setQuantity(50.0);
            product2.setInternalReference("INT-002");
            product2.setShellId(2L);
            product2.setRating(3.8);
            product2.setInventoryStatus(InventoryStatus.OUTOFSTOCK);
            product2.setDateCreated(LocalDateTime.now());
            product2.setLastUpdated(LocalDateTime.now());

            productRepository.save(product1);
            productRepository.save(product2);
        }
    }
}
