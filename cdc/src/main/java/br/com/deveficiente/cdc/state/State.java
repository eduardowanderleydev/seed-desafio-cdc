package br.com.deveficiente.cdc.state;

import br.com.deveficiente.cdc.country.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    public State() {}

    public State(@NotBlank String name, @NotNull Country country) {
        Assert.hasText(name, "State name cannot be empty");
        Assert.notNull(country, "Country cannot be null");
        this.name = name;
        this.country = country;
    }
}
