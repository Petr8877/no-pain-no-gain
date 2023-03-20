package nopainnogain.userservice.service.impl;

import nopainnogain.userservice.core.exception.SingleErrorResponse;
import nopainnogain.userservice.entity.Role;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class SendToAuditService {

    private static final String URL = "http://audit-service:8080/audit/secretlink";
//private static final String URL = "http://localhost:8080/audit/secretlink";
    public static void sendAudit(UUID uuid, String email, String fio, Role role, String text) throws IOException {
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
                          "id": 3
                        }
                        """.formatted(uuid, email, fio, role, text)))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }
}
