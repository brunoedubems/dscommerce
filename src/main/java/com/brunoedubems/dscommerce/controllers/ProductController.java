package com.brunoedubems.dscommerce.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunoedubems.dscommerce.dto.ProductDTO;
import com.brunoedubems.dscommerce.services.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
  // Optional<Product> result = productRepository.findById(1L);
  // Product product = result.get();
  // return product.getName();
  // // }
  // @GetMapping(value = "/{id}")
  // public ProductDTO findById(@PathVariable Long id) {
  // //ProductDTO dto = service.findById(id)
  // // return dto;
  // return service.findById(id);
  // }
  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

    ProductDTO dto = service.findById(id);
    return ResponseEntity.ok(dto); // customizando a resposta com o rensposeEntity
  }

  // @GetMapping()
  // public List<ProductDTO> findAll(Pageable pageable) {
  // return service.findAll(pageable);
  // }
  // @GetMapping()
  // public Page<ProductDTO> findAll(Pageable pageable) {
  // return service.findAll(pageable);
  // }
  @GetMapping()
  public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
    Page<ProductDTO> dto = service.findAll(pageable);
    return ResponseEntity.ok(dto);
  }

  // @PostMapping
  // public ProductDTO insert(@RequestBody ProductDTO dto) {

  // return service.insert(dto);
  // }
  @PostMapping
  public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {

    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();

    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
    dto = service.update(id, dto);
    return ResponseEntity.ok(dto); // customizando a resposta com o rensposeEntity
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
