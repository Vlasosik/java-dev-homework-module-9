package org.example;

import org.example.utilException.ImageException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpStatusImageDownloader {
    void downloadStatusImage(int code) throws ImageException {
        try {
            Files.createDirectories(Paths.get("src/main/java/org/example/img"));
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(new HttpStatusChecker().getStatusImage(code)))
                    .GET()
                    .build();
            String directory = "src/main/java/org/example/img/";
            String fileName = "image_status_" + code + ".jpg";
            HttpResponse<Path> send = client.send(request, HttpResponse.BodyHandlers.ofFile(Path.of(directory, fileName)));
            if (send.statusCode() != 200) {
                throw new ImageException("Failed to download the image for the status code: " + code);
            }
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
