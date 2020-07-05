package com.jose.bookingProducer;

import com.jose.bookingProducer.service.JMSSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingProducerApplicationTests {

	@Autowired
	private JMSSenderService JMSSenderService;

	@Test
	public void testReceive() {
		JMSSenderService.send("TestQueue", "TestMessage");
	}

}
