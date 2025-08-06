package com.agroflow.facturacion.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FACTURACION_QUEUE = "cola_facturacion";

    @Bean
    public Queue colaFacturacion() {
        return new Queue(FACTURACION_QUEUE, true);
    }
}
