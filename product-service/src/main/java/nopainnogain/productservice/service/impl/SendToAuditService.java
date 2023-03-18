package nopainnogain.productservice.service.impl;


import nopainnogain.productservice.core.Role;
import nopainnogain.productservice.core.exception.SingleErrorResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
public class SendToAuditService {

    private static final String URL = "http://audit-service:8080/api/v1/audit/secretlink";

    public static void sendAudit(UUID uuid, String email, String fio, Role role, String text, int id) throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                          "uuid": "%s",
                          "email": "%s",
                          "fio": "%s",
                          "role": "%s",
                          "text": "%s",
                          "id": "%s"
                        }
                        """.formatted(uuid, email, fio, role, text, id)))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }
}
