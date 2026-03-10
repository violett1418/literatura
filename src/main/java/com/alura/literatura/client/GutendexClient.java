package com.alura.literatura.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarLibro(String titulo) throws Exception {

        String url = BASE_URL + titulo.replace(" ", "%20");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}