package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryParty {

    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("account_number")
    private Integer accountNumber;
    @JsonProperty("account_number_code")
    private String accountNumberCode;
    @JsonProperty("account_type")
    private Integer accountType;
    private String address;
    @JsonProperty("bank_id")
    private Integer bankId;
    @JsonProperty("bank_id_code")
    private String bankIdCode;
    private String name;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumberCode() {
        return accountNumberCode;
    }

    public void setAccountNumberCode(String accountNumberCode) {
        this.accountNumberCode = accountNumberCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankIdCode() {
        return bankIdCode;
    }

    public void setBankIdCode(String bankIdCode) {
        this.bankIdCode = bankIdCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
