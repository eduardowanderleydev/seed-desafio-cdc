package br.com.deveficiente.cdc.author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 2 points of cognitive load
@RestController
public class AuthorController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("author/new")
    @Transactional
    public ResponseEntity newAuthor(@RequestBody @Valid NewAuthorForm form) {
        entityManager.persist(form.toModel());
        return ResponseEntity.ok().build();
    }
}
