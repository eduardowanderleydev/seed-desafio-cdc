package br.com.deveficiente.cdc.category;

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
public class CategoryController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/category/new")
    @Transactional
    public ResponseEntity newCategory(@RequestBody @Valid NewCategoryForm form) {
        entityManager.persist(form.toCategory());
        return ResponseEntity.ok().build();
    }
}
