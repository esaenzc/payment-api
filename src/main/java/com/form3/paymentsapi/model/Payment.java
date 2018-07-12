package com.form3.paymentsapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Payment.Builder.class)
public class Payment {

    public Payment () {
    }

    public Payment (Payment payment) {
    }

    @Id
    private String id;
    private String type;
    private Integer version;
    private String organisationId;
    private PaymentAttributes attributes;

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private String id;
        private String type;
        private Integer version;
        @JsonProperty("organisation_id")
        private String organisationId;
        private PaymentAttributes attributes;

        public Payment build () {
            Payment payment = new Payment ();
            payment.id = this.id;
            payment.type = this.type;
            payment.version = this.version;
            payment.organisationId = this.organisationId;
            payment.attributes = this.attributes;

            return payment;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setVersion(Integer version) {
            this.version = version;
            return this;
        }

        public Builder setOrganisationId(String organisationId) {
            this.organisationId = organisationId;
            return this;
        }

        public Builder setAttributes(PaymentAttributes attributes) {
            this.attributes = attributes;
            return this;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public PaymentAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(PaymentAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", version=" + version +
                ", organisationId='" + organisationId + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}