package br.com.deveficiente.cdc.author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
public class Author {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Author(@NotBlank String name, @NotBlank String email, @NotBlank String description) {
        Assert.hasText(name, "Name cannot be empty");
        Assert.hasText(email, "Email cannot be empty");
        Assert.hasText(description, "Description cannot be empty");

        this.name = name;
        this.email = email;
        this.description = description;
        createdAt = now();
    }

    /** Hibernate only */
    @Deprecated
    public Author() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }
}
