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

import com.mbbproject.spring.data.mongodb.model.Cash;
import com.mbbproject.spring.data.mongodb.repository.CashRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CashController {

  @Autowired
  CashRepository cashRepository;

  @GetMapping("/cash")
  public ResponseEntity<List<Cash>> getAllCashes(@RequestParam(required = false) String transactionType) {
    try {
      List<Cash> cashes = new ArrayList<Cash>();

      if (transactionType == null)
        cashRepository.findAll().forEach(cashes::add);
      else
        cashRepository.findByTransactionTypeContaining(transactionType).forEach(cashes::add);

      if (cashes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(cashes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/cash/{id}")
  public ResponseEntity<Cash> getCashesById(@PathVariable("id") String id) {
    Optional<Cash> cashData = cashRepository.findById(id);

    if (cashData.isPresent()) {
      return new ResponseEntity<>(cashData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/cash")
  public ResponseEntity<Cash> createCash(@RequestBody Cash cash) {
    try {
      Cash _cash = cashRepository.save(new Cash(cash.getTransactionType(), cash.getTransactionAmount(), cash.getAccountId()));
      return new ResponseEntity<>(_cash, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
