package br.com.deveficiente.cdc.book;

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
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/book/new")
    public ResponseEntity newBook(@RequestBody @Valid NewBookForm form){
        entityManager.persist(form.toModel(entityManager));
        return ResponseEntity.ok().build();
    }

}
