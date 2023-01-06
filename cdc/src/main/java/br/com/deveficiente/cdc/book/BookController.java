package br.com.deveficiente.cdc.book;

import br.com.deveficiente.cdc.shared.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/book/{id}")
    public ResponseEntity bookDetails(@PathVariable("id") Long bookId){
        Book book = Optional.ofNullable(entityManager.find(Book.class, bookId)).orElseThrow(() -> new ResourceNotFoundException("Book with id %s not found".formatted(bookId)));
        return ResponseEntity.ok(new BookDetailsView(book));
    }
}
