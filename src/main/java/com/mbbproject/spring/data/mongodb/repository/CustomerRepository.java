package com.mbbproject.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mbbproject.spring.data.mongodb.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
  List<Customer> findByNameContaining(String title);
}
