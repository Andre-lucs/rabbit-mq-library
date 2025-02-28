package com.andrelucs.rabbitlibraryapi.service.messaging.consumer;

import com.andrelucs.rabbitlibraryapi.service.LoanService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LoanConsumer {

    private final LoanService loanService;

    public LoanConsumer(LoanService loanService) {
        this.loanService = loanService;
    }

    @RabbitListener(queues = "loan-request-queue")
    public void receiveLoanRequest(String message) {
        System.out.println("Will process the request of id: " + message);
        Long id = Long.parseLong(message);
        loanService.processLoanRequest(id);
    }

}
