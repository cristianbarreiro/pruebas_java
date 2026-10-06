package com.example.apiclient;

import com.example.apiclient.model.Post;
import com.example.apiclient.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public ApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Post> getPosts() throws IOException, InterruptedException {
        String json = get("/posts");
        try {
            return mapper.readValue(json, new TypeReference<List<Post>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON inválido al listar posts: " + e.getMessage());
        }
    }

    public Post getPostById(int id) throws IOException, InterruptedException {
        String json = get("/posts/" + id);
        try {
            return mapper.readValue(json, Post.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON inválido al leer el post " + id + ": " + e.getMessage());
        }
    }

    public User getUserById(int id) throws IOException, InterruptedException {
        String json = get("/users/" + id);
        try {
            return mapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON inválido al leer el usuario " + id + ": " + e.getMessage());
        }
    }

    private String get(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL + path))
                .timeout(TIMEOUT)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (HttpTimeoutException e) {
            throw new RuntimeException("Timeout: la API no respondió en " + TIMEOUT.getSeconds() + "s");
        } catch (IOException e) {
            throw new RuntimeException("Error de conexión: " + e.getMessage());
        }

        int status = response.statusCode();
        if (status == 404) {
            throw new RuntimeException("HTTP 404: recurso no encontrado " + path);
        }
        if (status >= 400) {
            throw new RuntimeException("HTTP " + status + " en " + path);
        }
        return response.body();
    }
}
