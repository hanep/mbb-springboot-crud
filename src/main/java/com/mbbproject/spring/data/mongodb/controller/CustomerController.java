package com.mbbproject.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbbproject.spring.data.mongodb.model.Customer;
import com.mbbproject.spring.data.mongodb.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {

  @Autowired
  CustomerRepository customerRepository;

  @GetMapping("/customer")
  public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String name) {
    try {
      List<Customer> customers = new ArrayList<Customer>();

      if (name == null)
        customerRepository.findAll().forEach(customers::add);
      else
        customerRepository.findByNameContaining(name).forEach(customers::add);

      if (customers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(customers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getCustomersById(@PathVariable("id") String id) {
    Optional<Customer> customerData = customerRepository.findById(id);

    if (customerData.isPresent()) {
      return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/customer")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    try {
      Customer _customer = customerRepository.save(new Customer(customer.getName()));
      return new ResponseEntity<>(_customer, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
