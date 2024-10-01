package com.altenkata.alten_kata.repos;

import com.altenkata.alten_kata.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
