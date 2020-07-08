package com.jose.bookingProducer.service;

import com.jose.bookingProducer.dto.ReservationDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private static final String SUBJECT_RESERVATION = "Your Reservation";
    private static final String SUBJECT_MODIFY = "Your Modification of your Reservation";
    private static final String TEXT_RESERVE_ATTEMPT = "Hi %s,\nThis is an attempt to reserve the campsite, your reservation code is %s.\nWe will inform you as soon as your reservation is processed.";
    private static final String TEXT_MODIFY_ATTEMPT = "Hi %s,\nThis is an attempt to modify the campsite reservation, your reservation code is %s.\nWe will inform you as soon as your modification is processed.";

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a confirmation that your attempt to reserve the campsite was received.
     * @param reservationDTO
     */
    @Async
    @SneakyThrows
        public void sendEmailReservationConfirmation(ReservationDTO reservationDTO) {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(reservationDTO.getEmailAddress());

            msg.setSubject(SUBJECT_RESERVATION);
            msg.setText(String.format(TEXT_RESERVE_ATTEMPT, reservationDTO.getFirstMame(), reservationDTO.getReservationId()));
            mailSender.send(msg);
        }

    /**
     * Sends a confirmation that your attempt to reserve the campsite was received.
     * @param reservationDTO
     */
    @Async
    @SneakyThrows
    public void sendEmailModifyConfirmation(ReservationDTO reservationDTO) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(reservationDTO.getEmailAddress());

        msg.setSubject(SUBJECT_MODIFY);
        msg.setText(String.format(TEXT_MODIFY_ATTEMPT, reservationDTO.getFirstMame(), reservationDTO.getReservationId()));
        mailSender.send(msg);
    }
    }
