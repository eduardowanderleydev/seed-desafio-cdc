package br.com.deveficiente.cdc.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record NewAuthorForm(@NotBlank String name,
                            @NotBlank @Email String email,
                            @NotBlank @Length(max = 400) String description) {

    public Author toModel(){
        return new Author(this.name, this.email, this.description);
    }

}
