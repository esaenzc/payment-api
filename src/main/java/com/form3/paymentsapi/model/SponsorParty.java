package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SponsorParty {

    @JsonProperty("account_number")
    private Integer accountNumber;
    @JsonProperty("bank_id")
    private Integer bankId;
    @JsonProperty("bank_id_code")
    private String bankIdCode;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
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
}
