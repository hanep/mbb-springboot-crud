package com.mbbproject.spring.data.mongodb.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "cash")
public class Cash {
  @Id
  private String id;
  private String transactionType;
  private BigDecimal transactionAmount;
  private String accountId;

  public Cash() {

  }

  public Cash(String transactionType, BigDecimal transactionAmount, String accountId) {
    this.transactionType = transactionType;
    this.transactionAmount = transactionAmount;
    this.accountId = accountId;
  }
  public String getId() {
    return id;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public String getAccountId() {
    return accountId;
  }


  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @Override
  public String toString() {
    return "Cash [id=" + id + ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount + ", accountId=" + accountId + "]";
  }
}
