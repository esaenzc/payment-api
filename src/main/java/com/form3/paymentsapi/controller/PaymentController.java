package com.form3.paymentsapi.controller;

import com.form3.paymentsapi.model.Payment;
import com.form3.paymentsapi.model.PaymentWrapper;
import com.form3.paymentsapi.model.PaymentsWrapper;
import com.form3.paymentsapi.model.SelfLink;
import com.form3.paymentsapi.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.form3.paymentsapi.controller.Constant.PAYMENT;
import static com.form3.paymentsapi.controller.Constant.PAYMENT_SLASH;
import static com.form3.paymentsapi.controller.Constant.STEAM_PAYMENT;

@RestController
@RequestMapping(PAYMENT)
public class PaymentController {

    @Value("${server.hostname}")
    private String hostname;

    @Autowired
    private PaymentRepository paymentRepository;

    // HATEOAS link function
    private Function<Payment,PaymentWrapper> hypermedia = payment ->
            new PaymentWrapper(payment,new SelfLink(hostname + PAYMENT_SLASH + payment.getId()));


    // MAPPINGS

    @GetMapping(value = "/{id}")
    public Mono<PaymentWrapper> findById(@PathVariable String id) {
        return paymentRepository.findById(id).map(hypermedia);
    }

    @GetMapping(value = "/")
    public Mono<PaymentsWrapper> findAllPayments() {
        return paymentRepository.findAll()
                .map(hypermedia)
                .collectList()
                .map(list -> new PaymentsWrapper(list.stream()
                        .collect(Collectors.toList()),new SelfLink(hostname + PAYMENT_SLASH)));
    }

    // Payments are sent to the client as server sent events
    @GetMapping(value = STEAM_PAYMENT, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Payment> streamAllPayments() {
        return paymentRepository.findAll();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity> create(@Valid @RequestBody Payment payment) {
        return paymentRepository.existsById(payment.getId()).map(found -> {
            if (found) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepository.save(payment).map(hypermedia));

            }
        });
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaymentWrapper>> update(@PathVariable String id, @Valid @RequestBody Payment payment) {
        return paymentRepository.findById(id)
                .flatMap(existingPayment -> {
                    existingPayment.setId(payment.getId());
                    existingPayment.setOrganisationId(payment.getOrganisationId());
                    existingPayment.setAttributes(payment.getAttributes());
                    existingPayment.setType(payment.getType());
                    existingPayment.setVersion(payment.getVersion());
                    return paymentRepository.save(existingPayment).map(hypermedia);
                })
                .map(updatedPayment -> ResponseEntity.ok().body(updatedPayment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
        return paymentRepository.findById(id)
                .flatMap(existingPayment ->
                        paymentRepository.deleteById(id)
                       .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
