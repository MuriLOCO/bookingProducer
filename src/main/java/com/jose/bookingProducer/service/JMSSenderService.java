package com.jose.bookingProducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JMSSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSSenderService.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Async
    @SneakyThrows
    public <T> void send(String queue, T message){
        LOGGER.info("Sending message='{}'", message);

        objectMapper.registerModule(new JavaTimeModule());
        jmsMessagingTemplate.convertAndSend(queue, objectMapper.writeValueAsString(message));
    }
}
