package com.mbbproject.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
  @Id
  private String id;
  private String accountNumber;
  private String accountType;
  private String accountStatus;
  private String accountCustomerId;

  public Account() {

  }

  public Account(String accountNumber, String accountType, String accountStatus, String accountCustomerId) {
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.accountStatus = accountStatus;
    this.accountCustomerId = accountCustomerId;
  }

  public String getId() {
    return id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }

  public String getAccountCustomerId() {
    return accountCustomerId;
  }

  public void setAccountCustomerId(String accountCustomerId) {
    this.accountCustomerId = accountCustomerId;
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountStatus=" + accountStatus + ", accountCustomerId=" + accountCustomerId + "]";
  }
}
