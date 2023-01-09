package com.mbbproject.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mbbproject.spring.data.mongodb.model.Cash;

public interface CashRepository extends MongoRepository<Cash, String> {
  List<Cash> findByTransactionTypeContaining(String transactionType);
}
