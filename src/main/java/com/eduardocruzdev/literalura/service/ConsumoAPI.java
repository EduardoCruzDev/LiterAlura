package com.eduardocruzdev.literalura.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoAPI {

    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado HTTP
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                System.out.println("Error: Código de respuesta HTTP " + statusCode);
                return null; // No continuar si el código de estado no es 200 (OK)
            }

            // Verificar si el cuerpo de la respuesta es vacío
            String responseBody = response.body();
            if (responseBody == null || responseBody.isEmpty()) {
                System.out.println("Advertencia: El cuerpo de la respuesta está vacío");
                return null;
            }

            return responseBody;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
