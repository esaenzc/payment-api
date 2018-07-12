package com.form3.paymentsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.paymentsapi.model.Payment;
import com.form3.paymentsapi.repo.PaymentRepository;
import com.form3.paymentsapi.dummy.JsonDummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;

import static com.form3.paymentsapi.controller.Constant.*;

@RunWith(SpringRunner.class)
@WebFluxTest(PaymentController.class)
public class PaymentControllerTest {

    // Unit tests

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PaymentRepository repository;

    private Payment payment;

    @Before
    public void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        payment = objectMapper.readValue(JsonDummy.PAYMENTS.get(0), Payment.class);
    }

    @Test
    public void createPaymentRest() {
        BDDMockito.given(this.repository.existsById(Mockito.anyString()))
                .willReturn(Mono.just(false));
        BDDMockito.given(this.repository.save(Mockito.any()))
                .willReturn(Mono.just(payment));

        webTestClient.post().uri(PAYMENT_SLASH)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.payment").isNotEmpty()
                .jsonPath("$.payment.id").isEqualTo(payment.getId());
    }

    @Test
    public void createExistingPaymentRest() throws IOException {
        BDDMockito.given(this.repository.existsById(Mockito.anyString()))
                .willReturn(Mono.just(true));
        BDDMockito.given(this.repository.save(Mockito.any()))
                .willReturn(Mono.just(payment));

        webTestClient.post().uri(PAYMENT_SLASH)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);

    }

    @Test
    public void findPaymentByIdRest() throws IOException {
        BDDMockito.given(this.repository.findById(Mockito.anyString()))
                .willReturn(Mono.just(payment));

        webTestClient.get().uri(PAYMENT_SLASH + payment.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody()
                .jsonPath("$.payment.id").isEqualTo(payment.getId());
    }

    @Test
    public void findAllPaymentsRest() throws IOException {
        BDDMockito.given(this.repository.findAll())
                .willReturn(Flux.just(payment));

        webTestClient.get().uri(PAYMENT_SLASH)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody()
                .jsonPath("$.data").isNotEmpty();
    }

    @Test
    public void findAllPaymentsStreamRest() throws IOException {
        BDDMockito.given(this.repository.findAll())
                .willReturn(Flux.just(payment));

        webTestClient.get().uri(PAYMENT + STEAM_PAYMENT)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectHeader().contentType("text/event-stream;charset=UTF-8");
    }

    @Test
    public void updateExistingPaymentRest() throws IOException {
        BDDMockito.given(this.repository.findById(Mockito.anyString()))
                .willReturn(Mono.just(payment));
        BDDMockito.given(this.repository.save(Mockito.any()))
                .willReturn(Mono.just(payment));

        webTestClient.put().uri(PAYMENT_SLASH + payment.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void updateNonExistingPaymentRest() throws IOException {
        BDDMockito.given(this.repository.findById(Mockito.anyString()))
                .willReturn(Mono.empty());

        webTestClient.put().uri(PAYMENT_SLASH + payment.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void deletePaymentRest() throws IOException {
        BDDMockito.given(this.repository.findById(Mockito.anyString()))
                .willReturn(Mono.just(payment));
        BDDMockito.given(this.repository.deleteById(Mockito.anyString()))
                .willReturn(Mono.empty());

        webTestClient.delete().uri(PAYMENT_SLASH + payment.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteNonExistingPaymentRest() throws IOException {
        BDDMockito.given(this.repository.findById(Mockito.anyString()))
                .willReturn(Mono.empty());

        webTestClient.delete().uri(PAYMENT_SLASH + payment.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);

    }
}
