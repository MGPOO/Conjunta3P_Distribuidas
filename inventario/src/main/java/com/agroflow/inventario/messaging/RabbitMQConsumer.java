package com.agroflow.inventario.messaging;

import com.agroflow.inventario.model.Producto;
import com.agroflow.inventario.repository.ProductoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RabbitMQConsumer {

    private final ProductoRepository repository;
    private final ObjectMapper mapper = new ObjectMapper(); // para leer JSON

    public RabbitMQConsumer(ProductoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${cola.inventario}")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido desde RabbitMQ: " + mensaje);
        try {
            JsonNode json = mapper.readTree(mensaje);

            String productoNombre = json.path("payload").path("producto").asText();
            System.out.println("Producto recibido en payload: " + productoNombre);

            Optional<Producto> productoOpt = repository.findByNombre(productoNombre);
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                producto.setCantidad(producto.getCantidad() - 1);
                repository.save(producto);
                System.out.println("Inventario actualizado para: " + productoNombre);
            } else {
                System.err.println("Producto no encontrado: " + productoNombre);
            }

        } catch (Exception e) {
            System.err.println("Error procesando mensaje de inventario: " + e.getMessage());
        }
    }
}
