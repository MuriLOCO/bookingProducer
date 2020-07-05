package com.jose.bookingProducer.service;

import com.jose.bookingProducer.dto.ReservationDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private static final String QUEUE_NAME = "BOOKING.PROD";

    private final JMSSenderService JMSSenderService;
    private final MailSenderService mailSenderService;

    @Autowired
    public ReservationService(JMSSenderService JMSSenderService, MailSenderService mailSenderService){
        this.JMSSenderService = JMSSenderService;
        this.mailSenderService = mailSenderService;
    }

    /**
     * Creates and attempt to reserve the campsite
     * @param reservationDTO
     * @return
     */
    @SneakyThrows
    public ReservationDTO reserveCampsite(ReservationDTO reservationDTO){

        ReservationDTO builtReservationDTO = ReservationDTO.hiddenBuilder()
            .reservationId(UUID.randomUUID())
            .dayOfArrival(reservationDTO.getDayOfArrival())
            .cancelled(false)
            .firstMame(reservationDTO.getFirstMame())
            .lastName(reservationDTO.getLastName())
            .phoneNumber(reservationDTO.getPhoneNumber())
            .emailAddress(reservationDTO.getEmailAddress())
            .dateOfBirth(reservationDTO.getDateOfBirth()).build();

    JMSSenderService.send(QUEUE_NAME, builtReservationDTO);
    mailSenderService.sendEmailReservationConfirmation(builtReservationDTO);

    return builtReservationDTO;

    }
}
