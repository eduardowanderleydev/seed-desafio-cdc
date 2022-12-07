package br.com.deveficiente.cdc.author;

import java.time.LocalDateTime;

public class Author {
    private Long id;
    private String name;
    private String email;
    private String description;
    private LocalDateTime createdAt;

    public Author(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
