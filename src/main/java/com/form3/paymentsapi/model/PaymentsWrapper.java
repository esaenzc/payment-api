package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentsWrapper {

    public PaymentsWrapper(List<PaymentWrapper> payments, SelfLink selfLink) {
        this.payments = payments;
        this.selfLink = selfLink;
    }

    @JsonProperty("data")
    private List<PaymentWrapper> payments;
    @JsonProperty("links")
    private SelfLink selfLink;

    public List<PaymentWrapper> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentWrapper> payments) {
        this.payments = payments;
    }

    public SelfLink getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(SelfLink selfLink) {
        this.selfLink = selfLink;
    }
}
