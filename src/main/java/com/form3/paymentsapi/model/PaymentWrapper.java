package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentWrapper {

    public PaymentWrapper(Payment payment, SelfLink links) {
        this.payment = payment;
        this.links = links;
    }

    private Payment payment;
    @JsonProperty("links")
    private SelfLink links;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public SelfLink getLinks() {
        return links;
    }

    public void setLinks(SelfLink links) {
        this.links = links;
    }
}
