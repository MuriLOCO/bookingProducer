package com.jose.bookingProducer;

import com.jose.bookingProducer.dto.ReservationDTO;
import com.jose.bookingProducer.service.JMSSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

@SpringBootTest
public class BookingProducerApplicationTests {

	@Autowired
	private JMSSenderService JMSSenderService;

	@Test
	public void testSending() {

		ReservationDTO builtReservationDTO1 = ReservationDTO.hiddenBuilder()
				.reservationId(UUID.randomUUID())
				.dayOfArrival(Instant.now().plusSeconds(86400*20))
				.dayOfLeaving(Instant.now().plusSeconds(86400*23))
				.dayOfReservation(Instant.now())
				.cancelled(false)
				.firstMame("Jose")
				.lastName("Mendes")
				.phoneNumber("+15147999483")
				.emailAddress("jose.murilo.mendes@gmail.com")
				.dateOfBirth(Instant.parse("1986-11-08T00:00:00Z")).build();

		ReservationDTO builtReservationDTO2 = ReservationDTO.hiddenBuilder()
				.reservationId(UUID.randomUUID())
				.dayOfArrival(Instant.now().plusSeconds(86400*19))
				.dayOfLeaving(Instant.now().plusSeconds(86400*23))
				.dayOfReservation(Instant.now())
				.cancelled(false)
				.firstMame("Jose")
				.lastName("Mendes")
				.phoneNumber("+15147999483")
				.emailAddress("jose.murilo.mendes@gmail.com")
				.dateOfBirth(Instant.parse("1986-11-08T00:00:00Z")).build();

		ReservationDTO builtReservationDTO3 = ReservationDTO.hiddenBuilder()
				.reservationId(UUID.randomUUID())
				.dayOfArrival(Instant.now().plusSeconds(86400*3))
				.dayOfLeaving(Instant.now().plusSeconds(86400*6))
				.dayOfReservation(Instant.now())
				.cancelled(false)
				.firstMame("Jose")
				.lastName("Mendes")
				.phoneNumber("+15147999483")
				.emailAddress("jose.murilo.mendes@gmail.com")
				.dateOfBirth(Instant.parse("1986-11-08T00:00:00Z")).build();

		JMSSenderService.send("RTEST", builtReservationDTO1);
		JMSSenderService.send("RTEST", builtReservationDTO2);
		JMSSenderService.send("RTEST", builtReservationDTO3);
	}

}
