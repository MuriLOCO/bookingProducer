package com.jose.bookingProducer.service;

import com.jose.bookingProducer.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ReservationService {

    private static final String QUEUE_NAME_RESERVATION = "RESERVATION";
    private static final String QUEUE_NAME_MODIFY = "MODIFY";

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
    public ReservationDTO reserveCampsite(ReservationDTO reservationDTO){

        ReservationDTO builtReservationDTO = ReservationDTO.hiddenBuilder()
            .reservationId(UUID.randomUUID())
            .dayOfArrival(reservationDTO.getDayOfArrival())
            .dayOfLeaving(reservationDTO.getDayOfLeaving())
            .dayOfReservation(Instant.now())
            .cancelled(false)
            .firstMame(reservationDTO.getFirstMame())
            .lastName(reservationDTO.getLastName())
            .phoneNumber(reservationDTO.getPhoneNumber())
            .emailAddress(reservationDTO.getEmailAddress())
            .dateOfBirth(reservationDTO.getDateOfBirth()).build();

    JMSSenderService.send(QUEUE_NAME_RESERVATION, builtReservationDTO);
    mailSenderService.sendEmailReservationConfirmation(builtReservationDTO);

    return builtReservationDTO;

    }

    /**
     * Sends and attempt to modify the reservation
     * @param reservationDTO
     * @return
     */
    public ReservationDTO modifyReserve(ReservationDTO reservationDTO){
        ReservationDTO builtReservationDTO = ReservationDTO.hiddenBuilder()
                .reservationId(reservationDTO.getReservationId())
                .dayOfArrival(reservationDTO.getDayOfArrival())
                .dayOfLeaving(reservationDTO.getDayOfLeaving())
                .dayOfReservation(Instant.now())
                .cancelled(reservationDTO.getCancelled())
                .firstMame(reservationDTO.getFirstMame())
                .lastName(reservationDTO.getLastName())
                .phoneNumber(reservationDTO.getPhoneNumber())
                .emailAddress(reservationDTO.getEmailAddress())
                .dateOfBirth(reservationDTO.getDateOfBirth()).build();

        JMSSenderService.send(QUEUE_NAME_MODIFY, builtReservationDTO);
        mailSenderService.sendEmailModifyConfirmation(builtReservationDTO);

        return builtReservationDTO;
    }
}
