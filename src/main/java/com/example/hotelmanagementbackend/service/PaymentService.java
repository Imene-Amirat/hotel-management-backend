package com.example.hotelmanagementbackend.service;

import com.example.hotelmanagementbackend.dto.PaymentRequest;
import com.example.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.example.hotelmanagementbackend.model.Payment;
import com.example.hotelmanagementbackend.model.PaymentStatus;
import com.example.hotelmanagementbackend.model.Reservation;
import com.example.hotelmanagementbackend.model.ReservationStatus;
import com.example.hotelmanagementbackend.repository.PaymentRepository;
import com.example.hotelmanagementbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void createPayment(PaymentRequest p) {
        Reservation res = reservationRepository.findById(p.getReservation_id())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        // save payment
        paymentRepository.save(mappedEntity(p,res));

        // update reservation to CONFIRMED
        res.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(res);

    }

    private Payment mappedEntity(PaymentRequest p, Reservation res){
        Payment pay = new Payment();

        pay.setReservation(res);
        pay.setAmount(p.getAmount());
        pay.setStatus(PaymentStatus.PENDING);
        pay.setMethod(p.getMethod());
        pay.setCreated_at(p.getCreated_at());

        return pay;
    }
}
