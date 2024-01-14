package org.example;

import org.example.utilException.ImageException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    String getStatusImage(int code) throws ImageException {
        String url = "https://http.cat/" + code + ".jpg";
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<Void> send = client.send(request, HttpResponse.BodyHandlers.discarding());
            if (send.statusCode() == 404) {
                throw new ImageException("Failed to download the image for the status code: " + code);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return url;
    }
}