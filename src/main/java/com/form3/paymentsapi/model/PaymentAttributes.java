package com.form3.paymentsapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PaymentAttributes.Builder.class)
public class PaymentAttributes {
    private String amount;
    private BeneficiaryParty beneficiaryParty;
    private ChargesInformation chargesInformation;
    private String currency;
    private DebtorParty debtorParty;
    private String endToEndReference;
    private Fx fx;
    private Integer numericReference;
    private Long paymentId;
    private String paymentPurpose;
    private String paymentScheme;
    private String paymentType;
    private Date processingDate;
    private String reference;
    private String schemePaymentSubType;
    private String schemePaymentType;
    private SponsorParty sponsorParty;

    private PaymentAttributes () {}

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private String amount;
        @JsonProperty("beneficiary_party")
        private BeneficiaryParty beneficiaryParty;
        @JsonProperty("charges_information")
        private ChargesInformation chargesInformation;
        private String currency;
        @JsonProperty("debtor_party")
        private DebtorParty debtorParty;
        @JsonProperty("end_to_end_reference")
        private String endToEndReference;
        private Fx fx;
        @JsonProperty("numeric_reference")
        private Integer numericReference;
        @JsonProperty("payment_id")
        private Long paymentId;
        @JsonProperty("payment_purpose")
        private String paymentPurpose;
        @JsonProperty("payment_scheme")
        private String paymentScheme;
        @JsonProperty("payment_type")
        private String paymentType;
        @JsonProperty("processing_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date processingDate;
        private String reference;
        @JsonProperty("scheme_payment_sub_type")
        private String schemePaymentSubType;
        @JsonProperty("scheme_payment_type")
        private String schemePaymentType;
        @JsonProperty("sponsor_party")
        private SponsorParty sponsorParty;

        public PaymentAttributes build () {
            PaymentAttributes paymentAttr = new PaymentAttributes();
            paymentAttr.amount = this.amount;
            paymentAttr.beneficiaryParty = this.beneficiaryParty;
            paymentAttr.chargesInformation = this.chargesInformation;
            paymentAttr.currency = this.currency;
            paymentAttr.debtorParty = this.debtorParty;
            paymentAttr.endToEndReference = this.endToEndReference;
            paymentAttr.fx = this.fx;
            paymentAttr.numericReference = this.numericReference;
            paymentAttr.paymentId = this.paymentId;
            paymentAttr.paymentPurpose = this.paymentPurpose;
            paymentAttr.paymentScheme = this.paymentScheme;
            paymentAttr.paymentType = this.paymentType;
            paymentAttr.processingDate = this.processingDate;
            paymentAttr.reference = this.reference;
            paymentAttr.schemePaymentSubType = this.schemePaymentSubType;
            paymentAttr.schemePaymentType = this.schemePaymentType;
            paymentAttr.sponsorParty = this.sponsorParty;

            return paymentAttr;
        }

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder setBeneficiaryParty(BeneficiaryParty beneficiaryParty) {
            this.beneficiaryParty = beneficiaryParty;
            return this;
        }

        public Builder setChargesInformation(ChargesInformation chargesInformation) {
            this.chargesInformation = chargesInformation;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setDebtorParty(DebtorParty debtorParty) {
            this.debtorParty = debtorParty;
            return this;
        }

        public Builder setEndToEndReference(String endToEndReference) {
            this.endToEndReference = endToEndReference;
            return this;
        }

        public Builder setFx(Fx fx) {
            this.fx = fx;
            return this;
        }

        public Builder setNumericReference(Integer numericReference) {
            this.numericReference = numericReference;
            return this;
        }

        public Builder setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setPaymentPurpose(String paymentPurpose) {
            this.paymentPurpose = paymentPurpose;
            return this;
        }

        public Builder setPaymentScheme(String paymentScheme) {
            this.paymentScheme = paymentScheme;
            return this;
        }

        public Builder setPaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder setProcessingDate(Date processingDate) {
            this.processingDate = processingDate;
            return this;
        }

        public Builder setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder setSchemePaymentSubType(String schemePaymentSubType) {
            this.schemePaymentSubType = schemePaymentSubType;
            return this;
        }

        public Builder setSchemePaymentType(String schemePaymentType) {
            this.schemePaymentType = schemePaymentType;
            return this;
        }

        public Builder setSponsorParty(SponsorParty sponsorParty) {
            this.sponsorParty = sponsorParty;
            return this;
        }
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BeneficiaryParty getBeneficiaryParty() {
        return beneficiaryParty;
    }

    public void setBeneficiaryParty(BeneficiaryParty beneficiaryParty) {
        this.beneficiaryParty = beneficiaryParty;
    }

    public ChargesInformation getChargesInformation() {
        return chargesInformation;
    }

    public void setChargesInformation(ChargesInformation chargesInformation) {
        this.chargesInformation = chargesInformation;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public DebtorParty getDebtorParty() {
        return debtorParty;
    }

    public void setDebtorParty(DebtorParty debtorParty) {
        this.debtorParty = debtorParty;
    }

    public String getEndToEndReference() {
        return endToEndReference;
    }

    public void setEndToEndReference(String endToEndReference) {
        this.endToEndReference = endToEndReference;
    }

    public Fx getFx() {
        return fx;
    }

    public void setFx(Fx fx) {
        this.fx = fx;
    }

    public Integer getNumericReference() {
        return numericReference;
    }

    public void setNumericReference(Integer numericReference) {
        this.numericReference = numericReference;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentScheme() {
        return paymentScheme;
    }

    public void setPaymentScheme(String paymentScheme) {
        this.paymentScheme = paymentScheme;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSchemePaymentSubType() {
        return schemePaymentSubType;
    }

    public void setSchemePaymentSubType(String schemePaymentSubType) {
        this.schemePaymentSubType = schemePaymentSubType;
    }

    public String getSchemePaymentType() {
        return schemePaymentType;
    }

    public void setSchemePaymentType(String schemePaymentType) {
        this.schemePaymentType = schemePaymentType;
    }

    public SponsorParty getSponsorParty() {
        return sponsorParty;
    }

    public void setSponsorParty(SponsorParty sponsorParty) {
        this.sponsorParty = sponsorParty;
    }
}

