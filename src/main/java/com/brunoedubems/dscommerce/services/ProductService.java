package com.brunoedubems.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.brunoedubems.dscommerce.dto.ProductDTO;
import com.brunoedubems.dscommerce.entities.Product;
import com.brunoedubems.dscommerce.repositories.ProductRepository;
import com.brunoedubems.dscommerce.services.exceptions.DatabaseException;
import com.brunoedubems.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

//@Component  / service
@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  // @Transactional(readOnly = true)
  // public ProductDTO findById(Long id) {
  /// Optional<Product> result = repository.findById(id);
  // Product product = result.get(); // pega os dados do resultado.
  // ProductDTO dto = new ProductDTO(product);
  // ProductDTO dto = new ProductDTO(product.getId, product.getName.......);
  // poderia passar pelo construtor os dados individuais
  // dto.setId(product.getId()); poderia ser dessa forma inserido pelo set
  /// return dto;
  // Product product = repository.findById(id).get();
  // return new ProductDTO(product);
  // }
  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Product product = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("recurso não encontrado pelo id : " + id));
    return new ProductDTO(product);

  }

  // @Transactional(readOnly = true)
  // public List<ProductDTO> findAll(Pageable pageable) {
  // // List<Product> result = repository.findAll(pageable);
  // // return result.stream().map(x -> new ProductDTO(x)).toList();
  // }
  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> result = repository.findAll(pageable);
    return result.map(x -> new ProductDTO(x));
  }

  // @Transactional()
  // public ProductDTO insert(ProductDTO dto) {
  // Product entity = new Product();
  // entity.setName(dto.getName());
  // entity.setDescription(dto.getDescription());
  // entity.setPrice(dto.getPrice());
  // entity.setImgUrl(dto.getImgUrl());

  // entity = repository.save(entity);
  // return new ProductDTO(entity);

  // }
  @Transactional()
  public ProductDTO insert(ProductDTO dto) {
    Product entity = new Product();
    copyDtoEntity(dto, entity);
    entity = repository.save(entity);
    return new ProductDTO(entity);

  }

  @Transactional()
  public ProductDTO update(Long id, ProductDTO dto) {
    try {
      Product entity = repository.getReferenceById(id);
      copyDtoEntity(dto, entity);
      entity = repository.save(entity);
      return new ProductDTO(entity);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }

  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Recurso não encontrado - delete");
    }
    try {
      repository.deleteById(id);
      
    } catch (DataIntegrityViolationException e) {
       throw new DatabaseException("Falha de integridade referencial");
    }
  }

  private void copyDtoEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setImgUrl(dto.getImgUrl());
  }
}
