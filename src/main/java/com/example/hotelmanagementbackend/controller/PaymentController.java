package com.example.hotelmanagementbackend.controller;

import com.example.hotelmanagementbackend.dto.PaymentRequest;
import com.example.hotelmanagementbackend.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String,String>> createPayment(@Valid @RequestBody PaymentRequest p){
        paymentService.createPayment(p);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "successful payment is saved!"));
    }
}
