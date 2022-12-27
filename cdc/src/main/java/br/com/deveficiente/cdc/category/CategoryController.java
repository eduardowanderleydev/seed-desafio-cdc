package br.com.deveficiente.cdc.category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @PersistenceContext
    private EntityManager entityManager;

    private NewCategoryFormValidator newCategoryFormValidator;

    public CategoryController(NewCategoryFormValidator newCategoryFormValidator) {
        this.newCategoryFormValidator = newCategoryFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(newCategoryFormValidator);
    }

    @PostMapping("/category/new")
    @Transactional
    public ResponseEntity newCategory(@RequestBody @Valid NewCategoryForm form) {
        entityManager.persist(form.toCategory());
        return ResponseEntity.ok().build();
    }
}
