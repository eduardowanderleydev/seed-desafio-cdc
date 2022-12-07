package br.com.deveficiente.cdc.author;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 2 points of cognitive load
@RestController
@AllArgsConstructor
public class AuthorController {

    private final EntityManager entityManager;

    @PostMapping("author/new")
    public ResponseEntity newAuthor(@Valid @RequestBody NewAuthorForm form, BindingResult result) {
        if (result.hasErrors()) throw new RuntimeException("i'll change it soon");
        entityManager.persist(form.toModel());
        return ResponseEntity.ok().build();
    }
}
