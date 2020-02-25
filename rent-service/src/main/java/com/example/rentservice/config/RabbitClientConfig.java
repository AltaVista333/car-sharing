package com.example.rentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitClientConfig {

    @Value("${fromClient}")
    private String queueFromClient;

    @Value("${toClient}")
    private String queueToClient;

    @Value("${exchange.fromClient}")
    private String fanoutExchangeFromClient;

    @Value("${exchange.toClient}")
    private String fanoutExchangeToClient;


    @Bean
    @Qualifier("fromClient")
    public FanoutExchange fanoutExchangeFromClient() {
        return new FanoutExchange(fanoutExchangeFromClient);
    }

    @Bean
    @Qualifier("toClient")
    public FanoutExchange fanoutExchangeToClient() {
        return new FanoutExchange(fanoutExchangeToClient);
    }

    @Bean
    @Qualifier("fromClient")
    public Queue queueFromClient() {
        return new Queue(queueFromClient);
    }

    @Bean
    @Qualifier("toClient")
    public Queue queueToClient() {
        return new Queue(queueToClient);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFromClientFanout(@Qualifier("fromClient") FanoutExchange fanoutExchange, @Qualifier("fromClient") Queue queueA) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueToClientFanout(@Qualifier("toClient") FanoutExchange fanoutExchange, @Qualifier("toClient") Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
