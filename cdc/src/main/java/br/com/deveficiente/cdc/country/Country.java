package br.com.deveficiente.cdc.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Country() {}

    public Country(@NotNull String name) {
        Assert.hasText(name, "Country name cannot be null");
        this.name = name;
    }
}
