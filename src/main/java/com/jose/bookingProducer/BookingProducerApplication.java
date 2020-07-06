package com.jose.bookingProducer;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BookingProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingProducerApplication.class, args);
	}

}