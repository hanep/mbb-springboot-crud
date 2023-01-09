package com.mbbproject.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {
  @Id
  private String id;
  private String accountNumber;
  private String accountType;
  private String accountStatus;

  public Account() {

  }

  public Account(String accountNumber, String accountType, String accountStatus) {
    this.accountNumber = accountNumber;
    this.accountType = accountType;
    this.accountStatus = accountStatus;
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

  @Override
  public String toString() {
    return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountStatus=" + accountStatus + "]";
  }
}
