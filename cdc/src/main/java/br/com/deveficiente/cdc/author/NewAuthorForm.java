package br.com.deveficiente.cdc.author;

import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record NewAuthorForm(@NotBlank(message ="{author.name.not.blank}") String name,
                            @NotBlank @Email (message = "{author.email.must.be.valid}") @UniqueValue(domainClass = Author.class, fieldName = "email") String email,
                            @NotBlank(message = "{author.description.not.blank}") @Length(max = 400) String description) {

    public Author toModel(){
        return new Author(this.name, this.email, this.description);
    }

}
