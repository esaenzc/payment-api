package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargesInformation {
    @JsonProperty("bearer_code")
    private String bearerCode;
    @JsonProperty("sender_charges")
    private List<SenderCharges> senderCharges;
    @JsonProperty("receiver_charges_amount")
    private String receiverChargesAmount;
    @JsonProperty("receiver_charges_currency")
    private String receiverChargesCurrency;

    public String getBearerCode() {
        return bearerCode;
    }

    public void setBearerCode(String bearerCode) {
        this.bearerCode = bearerCode;
    }

    public List<SenderCharges> getSenderCharges() {
        return senderCharges;
    }

    public void setSenderCharges(List<SenderCharges> senderCharges) {
        this.senderCharges = senderCharges;
    }

    public String getReceiverChargesAmount() {
        return receiverChargesAmount;
    }

    public void setReceiverChargesAmount(String receiverChargesAmount) {
        this.receiverChargesAmount = receiverChargesAmount;
    }

    public String getReceiverChargesCurrency() {
        return receiverChargesCurrency;
    }

    public void setReceiverChargesCurrency(String receiverChargesCurrency) {
        this.receiverChargesCurrency = receiverChargesCurrency;
    }
}
