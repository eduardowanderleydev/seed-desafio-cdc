package br.com.deveficiente.cdc.state;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 2 points of cognite load
@RestController
public class StateController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/state/new")
    @Transactional
    public ResponseEntity newState(@RequestBody @Valid NewStateForm form) {
        entityManager.persist(form.toModel(entityManager));
        return ResponseEntity.ok().build();
    }
}
