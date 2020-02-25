package com.example.carservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConf {

    @Value("${fromRent}")
    private String queueFromCar;

    @Value("${toRent}")
    private String queueToCar;

    @Value("${exchange.fromRent}")
    private String fanoutExchangeFromCar;

    @Value("${exchange.toRent}")
    private String fanoutExchangeToCar;


    @Bean
    @Qualifier("fromRent")
    public FanoutExchange fanoutExchangeFromCar() {
        return new FanoutExchange(fanoutExchangeFromCar);
    }

    @Bean
    @Qualifier("toRent")
    public FanoutExchange fanoutExchangeToCar() {
        return new FanoutExchange(fanoutExchangeToCar);
    }

    @Bean
    @Qualifier("fromRent")
    public Queue queueFromCar() {
        return new Queue(queueFromCar);
    }

    @Bean
    @Qualifier("toRent")
    public Queue queueToCar() {
        return new Queue(queueToCar);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueEFanout(@Qualifier("fromRent") FanoutExchange fanoutExchange, @Qualifier("fromRent") Queue queueA) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeQueueFFanout(@Qualifier("toRent")FanoutExchange fanoutExchange,@Qualifier("toRent") Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }


}
