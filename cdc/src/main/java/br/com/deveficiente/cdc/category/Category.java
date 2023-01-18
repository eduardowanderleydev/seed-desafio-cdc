package br.com.deveficiente.cdc.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    /** Hibernate only */
    @Deprecated
    public Category() {}

    public Category(String name) {
        Assert.hasText(name, "Category name cannot be null");
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
}
