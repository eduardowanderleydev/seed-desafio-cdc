package br.com.deveficiente.cdc.author;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NewAuthorFormValidator implements Validator {

    private AuthorRepository authorRepository;

    public NewAuthorFormValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) return;

        NewAuthorForm form = (NewAuthorForm) target;

        Optional<Author> possibleAuthor = authorRepository.findByEmail(form.email());

        if (possibleAuthor.isPresent()) {
            errors.rejectValue("email", null, "Already exists an author with email " + form.email());
        }
    }
}
