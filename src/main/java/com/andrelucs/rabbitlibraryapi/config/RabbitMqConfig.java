package com.andrelucs.rabbitlibraryapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

    @Bean
    public Queue loanRequestQueue() {
        return new Queue("loan-request-queue");
    }

    @Bean
    public DirectExchange loanRequestExchange() {
        return new DirectExchange("loan-request-exchange");
    }

    @Bean
    public Binding loanBinding(Queue loanRequestQueue, DirectExchange loanRequestExchange) {
        return BindingBuilder.bind(loanRequestQueue).to(loanRequestExchange).withQueueName();
    }

}
