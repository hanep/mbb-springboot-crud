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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbbproject.spring.data.mongodb.model.Account;
import com.mbbproject.spring.data.mongodb.repository.AccountRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AccountController {

  @Autowired
  AccountRepository accountRepository;

  @GetMapping("/account")
  public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) String accountNumber) {
    try {
      List<Account> accounts = new ArrayList<Account>();

      if (accountNumber == null)
        accountRepository.findAll().forEach(accounts::add);
      else
        accountRepository.findByAccountNumberContaining(accountNumber).forEach(accounts::add);

      if (accounts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(accounts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/account/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable("id") String id) {
    Optional<Account> accountData = accountRepository.findById(id);

    if (accountData.isPresent()) {
      return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/account")
  public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    try {
      Account _account = accountRepository.save(new Account(account.getAccountNumber(), account.getAccountType(), account.getAccountStatus(), account.getAccountCustomerId()));
      return new ResponseEntity<>(_account, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/account/{id}")
  public ResponseEntity<Account> updateAccount(@PathVariable("id") String id, @RequestBody Account account) {
    Optional<Account> accountData = accountRepository.findById(id);

    if (accountData.isPresent()) {
      Account _account = accountData.get();
      _account.setAccountNumber(account.getAccountNumber());
      _account.setAccountType(account.getAccountType());
      _account.setAccountStatus(account.getAccountStatus());
      return new ResponseEntity<>(accountRepository.save(_account), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
