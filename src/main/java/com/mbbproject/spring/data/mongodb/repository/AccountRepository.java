package com.mbbproject.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mbbproject.spring.data.mongodb.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
  List<Account> findByAccountNumberContaining(String accountNumber);
}
