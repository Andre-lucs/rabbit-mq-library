package com.andrelucs.rabbitlibraryapi.service.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoanProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public LoanProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendLoanRequest(Long loanRequestDTO) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(loanRequestDTO);
        amqpTemplate.convertAndSend("loan-request-exchange", "loan-request-queue", message);
    }

}
