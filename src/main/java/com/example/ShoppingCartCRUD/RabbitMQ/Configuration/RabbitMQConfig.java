package com.example.ShoppingCartCRUD.RabbitMQ.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange cartExchange() {
        return new TopicExchange("cart_exchange");
    }

    @Bean
    public Queue cartQueue() {
        return new Queue("cart_queue");
    }

    @Bean
    public Binding cartBinding(Queue cartQueue, TopicExchange cartExchange) {
        return BindingBuilder.bind(cartQueue).to(cartExchange).with("cart.*");
    }
}
