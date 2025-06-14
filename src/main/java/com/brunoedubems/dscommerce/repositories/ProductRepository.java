package com.brunoedubems.dscommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brunoedubems.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

  
} 