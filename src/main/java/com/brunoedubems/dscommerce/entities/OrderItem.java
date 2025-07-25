package com.brunoedubems.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

  @EmbeddedId
  private OrderItemPK id = new OrderItemPK();

  private Integer quantity;
  private Double price;

  public OrderItem() {
  }

  public OrderItem(Order order, Product product, Integer quantity, Double price) {
    id.setOrder(order); // CONSTRUINDO A ATRIBUIÇÃO PARA O ID o orderItemPK
    id.setProduct(product);
    this.quantity = quantity;
    this.price = price;
  }

  public Order getOrder() {
    return id.getOrder();
  }

  public void setOrder(Order order) {
    id.setOrder(order);
  }

  public Product getProduct() {
    return id.getProduct();
  }

  public void setProduct(Product product) {
    id.setProduct(product);
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderItem other = (OrderItem) obj;
    if (id == null) {
      return other.id == null;
    } else {
      return id.equals(other.id);
    }
  }

}
