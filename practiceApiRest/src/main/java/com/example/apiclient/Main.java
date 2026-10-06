package com.example.apiclient;

import com.example.apiclient.model.Post;
import com.example.apiclient.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Consultando API...");
        System.out.println();

        ApiClient client = new ApiClient();

        System.out.println("GET /posts");
        try {
            List<Post> posts = client.getPosts();
            System.out.println("✓ " + posts.size() + " posts obtenidos");
        } catch (Exception e) {
            System.out.println("✗ " + e.getMessage());
        }
        System.out.println();

        System.out.println("GET /posts/1");
        try {
            Post post = client.getPostById(1);
            System.out.println("✓ Post encontrado: " + post.getId());
            System.out.println();
            System.out.println("Post encontrado");
            System.out.println("----------------");
            System.out.println("ID: " + post.getId());
            System.out.println("Usuario: " + post.getUserId());
            System.out.println("Título: " + post.getTitle());
            System.out.println("Contenido: " + post.getBody());
        } catch (Exception e) {
            System.out.println("✗ " + e.getMessage());
        }
        System.out.println();

        System.out.println("GET /users/1");
        try {
            User user = client.getUserById(1);
            System.out.println("✓ Usuario encontrado: " + user.getId());
            System.out.println("  Nombre: " + user.getName());
            System.out.println("  Email: " + user.getEmail());
        } catch (Exception e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
}
