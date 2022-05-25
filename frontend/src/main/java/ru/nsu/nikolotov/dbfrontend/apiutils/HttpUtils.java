package ru.nsu.nikolotov.dbfrontend.apiutils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpUtils {
    public static <T> CompletableFuture<T> doAndParseGETRequest(URI uri, Class<T> tClass, String messageOnError) {
        HttpClient httpClient = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(resp -> {
                    if (resp.statusCode() != 200) {
                        JOptionPane.showMessageDialog(null, messageOnError);
                    }
                    return resp;
                })
                .thenApply(HttpResponse::body)
                .thenApply(s -> parseRequest(s, tClass));
    }

    public static <T> CompletableFuture<T> doAndParsePUTRequest(URI uri, Class<T> tClass, String messageOnError) {
        HttpClient httpClient = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(resp -> {
                    if (resp.statusCode() != 200) {
                        JOptionPane.showMessageDialog(null, messageOnError);
                    }
                    return resp;
                })
                .thenApply(HttpResponse::body)
                .thenApply(s -> parseRequest(s, tClass));
    }


    private static <T> T parseRequest(String rawResp, Class<T> clazz) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            var parsedResp = objectMapper.readValue(rawResp, clazz);
            return parsedResp;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Can't parse response!");
            e.printStackTrace();
            return null;
        }
    }

    private HttpRequest buildRequest(String uriString) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uriString))
                .build();
    }
}
