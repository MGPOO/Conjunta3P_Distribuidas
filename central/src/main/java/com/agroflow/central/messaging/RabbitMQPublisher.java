package com.agroflow.central.messaging;

import com.agroflow.central.config.RabbitMQConfig;
import com.agroflow.central.model.Cosecha;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;

@Component
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarNuevaCosecha(Cosecha cosecha) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("cosecha_id", cosecha.getId().toString());
            payload.put("producto", cosecha.getProducto());
            payload.put("toneladas", cosecha.getToneladas());

            // Agregamos insumos requeridos según producto
            if (cosecha.getProducto().equalsIgnoreCase("Arroz Oro")) {
                payload.put("requiere_insumos", List.of("Semilla Arroz L-23", "Fertilizante N-PK"));
            } else {
                payload.put("requiere_insumos", Collections.emptyList());
            }

            Map<String, Object> evento = new HashMap<>();
            evento.put("event_id", UUID.randomUUID().toString());
            evento.put("event_type", "nueva_cosecha");
            evento.put("timestamp", ZonedDateTime.now().toString());
            evento.put("payload", payload);

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    mapper.writeValueAsString(evento)
            );

            System.out.println("✅ Evento 'nueva_cosecha' publicado");
        } catch (Exception e) {
            System.err.println("❌ Error al publicar evento: " + e.getMessage());
        }
    }
}
