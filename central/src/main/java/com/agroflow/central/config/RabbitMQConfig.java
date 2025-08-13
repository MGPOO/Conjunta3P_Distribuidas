package com.agroflow.central.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "cosechas";
    public static final String ROUTING_KEY = "nueva";
    public static final String QUEUE_FACTURACION = "cola_facturacion";
    public static final String QUEUE_INVENTARIO = "cola_inventario";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue colaFacturacion() {
        return new Queue(QUEUE_FACTURACION, true);
    }

    @Bean
    public Queue colaInventario() {
        return new Queue(QUEUE_INVENTARIO, true);
    }

    @Bean
    public Binding bindingFacturacion() {
        return BindingBuilder.bind(colaFacturacion()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingInventario() {
        return BindingBuilder.bind(colaInventario()).to(exchange()).with(ROUTING_KEY);
    }
}
