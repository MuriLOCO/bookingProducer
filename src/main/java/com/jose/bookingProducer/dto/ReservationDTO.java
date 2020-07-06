package com.jose.bookingProducer.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder(builderMethodName = "hiddenBuilder")
@Getter
@EqualsAndHashCode
public class ReservationDTO {


    final UUID reservationId;
    final Instant dayOfReservation;
    final Instant dayOfArrival;
    final Instant dayOfLeaving;
    final Boolean cancelled;
    final String firstMame;
    final String lastName;
    final String phoneNumber;
    final String emailAddress;
    final Instant dateOfBirth;
}
