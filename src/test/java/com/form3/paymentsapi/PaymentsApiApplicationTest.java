package com.form3.paymentsapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.paymentsapi.controller.Constant;
import com.form3.paymentsapi.dummy.JsonDummy;
import com.form3.paymentsapi.model.Payment;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentsApiApplicationTest {

	// Integration tests

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebTestClient webTestClient;

	private ObjectMapper objectMapper = new ObjectMapper();

	@After
	public void cleanUp () throws IOException {
		Payment payment = objectMapper.readValue(JsonDummy.PAYMENTS.get(0), Payment.class);
		webTestClient.delete().uri(Constant.PAYMENT_SLASH + payment.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange();
	}

	@Test
	public void createPaymentsParallelAsyncNonBloking() throws IOException {
		JsonDummy.PAYMENTS.parallelStream().forEach(paymentDummy -> {
			try {
				Payment payment = objectMapper.readValue(paymentDummy, Payment.class);
				log.info("Send Payment:" + payment.getId());
				EntityExchangeResult<byte[]> exchange = webTestClient.post().uri(Constant.PAYMENT_SLASH)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.body(Mono.just(paymentDummy), String.class)
						.exchange()
						.expectStatus().isCreated()
						.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
						.expectBody()
						.jsonPath("$.payment").isNotEmpty()
						.jsonPath("$.payment.id").isEqualTo(payment.getId())
						.returnResult();
				log.info("Payment:" + payment.getId() + " status: " + exchange.getStatus());
			} catch (IOException exception) {
				throw new AssertionError(exception.getMessage());
			}});
	}

	@Test
	public void findPayments() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Payment payment = objectMapper.readValue(JsonDummy.PAYMENTS.get(0), Payment.class);

		// Create payment
		webTestClient.post().uri(Constant.PAYMENT_SLASH)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(payment), Payment.class)
				.exchange()
				.expectStatus().isCreated();

		// Find payments
		webTestClient.get().uri(Constant.PAYMENT_SLASH)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
				.jsonPath("$.data").isNotEmpty();
	}

	@Test
	public void updatePayment() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Payment payment = objectMapper.readValue(JsonDummy.PAYMENTS.get(0), Payment.class);
		String newId = "newId";
		String oldId = payment.getId();

		// Create payment
		webTestClient.post().uri(Constant.PAYMENT_SLASH)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
				.exchange()
				.expectStatus().isCreated();

		payment.setId(newId);

		// Update payment
		webTestClient.put().uri(Constant.PAYMENT_SLASH + oldId)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(payment), Payment.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
				.jsonPath("$.payment").isNotEmpty()
				.jsonPath("$.payment.id").isEqualTo(newId);
	}

	@Test
	public void deletePayment() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Payment payment = objectMapper.readValue(JsonDummy.PAYMENTS.get(0), Payment.class);

		// Create payment
		webTestClient.post().uri(Constant.PAYMENT_SLASH)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(JsonDummy.PAYMENTS.get(0)), String.class)
				.exchange()
				.expectStatus().isCreated();

		// Update payment
		webTestClient.delete().uri(Constant.PAYMENT_SLASH + payment.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk();
	}

}
