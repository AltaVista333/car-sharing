package com.example.clientservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabConf {

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
    public FanoutExchange fanoutExchangeFromCar() {
        return new FanoutExchange(fanoutExchangeFromClient);
    }

    @Bean
    @Qualifier("toClient")
    public FanoutExchange fanoutExchangeToCar() {
        return new FanoutExchange(fanoutExchangeToClient);
    }

    @Bean
    @Qualifier("fromClient")
    public Queue queueFromCar() {
        return new Queue(queueFromClient);
    }

    @Bean
    @Qualifier("toClient")
    public Queue queueToCar() {
        return new Queue(queueToClient);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueEFanout(@Qualifier("fromClient") FanoutExchange fanoutExchange, @Qualifier("fromClient") Queue queueA) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFFanout(@Qualifier("toClient") FanoutExchange fanoutExchange, @Qualifier("toClient") Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }


}
