package com.mbbproject.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
  @Id
  private String id;
  private String name;

  public Customer() {

  }

  public Customer(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", title=" + name + "]";
  }
}
