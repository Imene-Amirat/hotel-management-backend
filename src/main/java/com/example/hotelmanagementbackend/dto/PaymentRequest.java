package com.example.hotelmanagementbackend.dto;

import com.example.hotelmanagementbackend.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull(message = "reservation id is required")
    private int reservationId;

    @NotNull(message = "amount is required")
    private Double amount;

    @NotNull(message = "Payment Method is required")
    private PaymentMethod method;

    @NotNull(message = "created at is required")
    private LocalDateTime created_at;

    private LocalDateTime  paid_at;
}
