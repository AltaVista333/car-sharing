package com.example.rentservice.config;

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
public class RabCarConf {

    @Value("${fromCar}")
    private String queueFromCar;

    @Value("${toCar}")
    private String queueToCar;

    @Value("${exchange.fromCar}")
    private String fanoutExchangeFromCar;

    @Value("${exchange.toCar}")
    private String fanoutExchangeToCar;


    @Bean
    @Qualifier("fromCar")
    public FanoutExchange fanoutExchangeFromCar() {
        return new FanoutExchange(fanoutExchangeFromCar);
    }

    @Bean
    @Qualifier("toCar")
    public FanoutExchange fanoutExchangeToCar() {
        return new FanoutExchange(fanoutExchangeToCar);
    }

    @Bean
    @Qualifier("fromCar")
    public Queue queueFromCar() {
        return new Queue(queueFromCar);
    }

    @Bean
    @Qualifier("toCar")
    public Queue queueToCar() {
        return new Queue(queueToCar);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueEFanout(@Qualifier("fromCar") FanoutExchange fanoutExchange, @Qualifier("fromCar") Queue queueA) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFFanout(@Qualifier("toCar") FanoutExchange fanoutExchange, @Qualifier("toCar") Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }


}
