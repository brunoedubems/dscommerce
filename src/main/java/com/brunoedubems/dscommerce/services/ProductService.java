package com.brunoedubems.dscommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoedubems.dscommerce.dto.ProductDTO;
import com.brunoedubems.dscommerce.entities.Product;
import com.brunoedubems.dscommerce.repositories.ProductRepository;

//@Component  / service
@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
   /// Optional<Product> result = repository.findById(id);
   ////Product product = result.get(); // pega os dados do resultado.
   //// ProductDTO dto = new ProductDTO(product);
    // ProductDTO dto = new ProductDTO(product.getId, product.getName.......);
    // poderia passar pelo construtor os dados individuais
    // dto.setId(product.getId()); poderia ser dessa forma inserido pelo set
    /// return dto;
    Product product = repository.findById(id).get();
    return new ProductDTO(product);
  }

}
