package br.com.deveficiente.cdc.book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 4 points of cognitive load
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

    @GetMapping("/books")
    public ResponseEntity listBooks() {
        List<BookView> books = (List<BookView>) entityManager.createQuery("select new br.com.deveficiente.cdc.book.BookView(b.id, b.title) from Book b").getResultList();
        return ResponseEntity.ok(books);
    }
}
