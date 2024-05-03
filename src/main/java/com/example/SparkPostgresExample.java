package com.example;

import static spark.Spark.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SparkPostgresExample {

    public static void main(String[] args) {
        // Configure Spark
        staticFiles.location("/pages");
        port(4567);

        // Define route to serve login.html
        get("/form", (req, res) -> {
            try {
                return new String(Files.readAllBytes(Paths.get("pages\\CadastroPessoal\\login.html")));
            } catch (IOException e) {
                e.printStackTrace();
                return "An error occurred while reading login.html";
            }
        });

        post("/submit", (req, res) -> {
            String senha = req.queryParams("senha");
            String email = req.queryParams("email");
            String nome = req.queryParams("nome");
            String sobrenome = req.queryParams("sobrenome");
            String cpf = req.queryParams("cpf");

            // Conectar ao banco de dados PostgreSQL
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "2307123")) {
                // Inserir dados na tabela
                String sql = "INSERT INTO usuarios (senha, email,  nome, sobrenome, cpf) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, senha);
                    statement.setString(2, email);
                    statement.setString(3, nome);
                    statement.setString(4, sobrenome);
                    statement.setString(5, cpf);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Deu bom");
                        return new String(Files.readAllBytes(Paths.get("pages\\Home Gestão financeira\\home.html")));
                    } else {
                        System.out.println("Deu ruim");
                        return "Falha ao inserir dados.";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Ocorreu um erro.";
            }
        });

    }
}
