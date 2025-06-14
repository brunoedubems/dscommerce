package com.brunoedubems.dscommerce.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.brunoedubems.dscommerce.dto.ProductDTO;
import com.brunoedubems.dscommerce.services.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  @Autowired
 private ProductService service;
  // private ProductRepository productRepository;

  @RequestMapping(value = "/primeira", method = RequestMethod.GET)
  public String requestMethodName() {
    return "Primeira rota com RequestMapping";
  }

  @GetMapping("/segunda")
  public String getMethodName() {
    return "segunda rota com getmapping";
  }

  // @GetMapping("/teste")
  // public String teste() {
  //   Optional<Product> result = productRepository.findById(1L);
  //   Product product = result.get();
  //   return product.getName();
  // }
  @GetMapping(value = "/{id}")
  public ProductDTO findById(@PathVariable Long id) {
   //ProductDTO dto = service.findById(id)
   // return dto;
   return service.findById(id);
  }
  // @GetMapping()
  // public List<ProductDTO> findAll(Pageable pageable) {
  //  return service.findAll(pageable);
  // }
  @GetMapping()
  public Page<ProductDTO> findAll(Pageable pageable) {
   return service.findAll(pageable);
  }


}
