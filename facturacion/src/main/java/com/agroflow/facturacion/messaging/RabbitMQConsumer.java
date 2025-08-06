package com.agroflow.facturacion.messaging;

import com.agroflow.facturacion.model.Factura;
import com.agroflow.facturacion.service.FacturaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQConsumer {

    private final FacturaService facturaService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${central.api.url}")
    private String centralApiUrl;

    public RabbitMQConsumer(FacturaService facturaService) {
        this.facturaService = facturaService;
        this.restTemplate = new RestTemplate();
    }

    @RabbitListener(queues = "cola_facturacion")
    public void recibirMensaje(String mensajeJson) {
        try {
            JsonNode root = objectMapper.readTree(mensajeJson);
            JsonNode payload = root.path("payload");

            String cosechaId = payload.path("cosecha_id").asText();
            String producto = payload.path("producto").asText();
            double toneladas = payload.path("toneladas").asDouble();

            Factura factura = facturaService.crearFactura(cosechaId, producto, toneladas);

            String url = centralApiUrl + "/cosechas/" + cosechaId + "/estado";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String jsonBody = """
            {
              "estado": "FACTURADA",
              "factura_id": "%s"
            }
            """.formatted(factura.getFacturaId());

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
                System.out.println("✅ Notificación exitosa a Central. Respuesta: " + response.getStatusCode());
            } catch (Exception ex) {
                System.err.println("⚠️ No se pudo notificar a Central: " + ex.getMessage());
                // Aquí puedes guardar en una tabla de errores pendientes o en un log
            }

        } catch (Exception e) {
            System.err.println("❌ Error procesando mensaje de RabbitMQ: " + e.getMessage());
        }
    }
}
