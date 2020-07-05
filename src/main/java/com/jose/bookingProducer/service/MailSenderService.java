package com.jose.bookingProducer.service;

import com.jose.bookingProducer.dto.ReservationDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private static final String SUBJECT = "Reservation Services";
    private static final String TEXT = "Hi %s,\nThis is an attempt to reserve the campsite, your reservation code is %s.\nWe will inform you as soon as your reservation is processed.";

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a confirmation that your attempt to reserve the campsite was received.
     * @param reservationDTO
     */
    @SneakyThrows
        public void sendEmailReservationConfirmation(ReservationDTO reservationDTO) {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(reservationDTO.getEmailAddress());

            msg.setSubject(SUBJECT);
            msg.setText(String.format(TEXT, reservationDTO.getFirstMame(), reservationDTO.getReservationId()));
            mailSender.send(msg);
        }
    }
