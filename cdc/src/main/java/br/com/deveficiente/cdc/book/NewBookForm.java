package br.com.deveficiente.cdc.book;

import br.com.deveficiente.cdc.author.Author;
import br.com.deveficiente.cdc.category.Category;
import br.com.deveficiente.cdc.shared.exceptions.ResourceNotFoundException;
import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

// 5 points of cognitive load
public record NewBookForm(@NotBlank @UniqueValue(domainClass = Book.class, fieldName = "title") String title,
                          @NotBlank @Length(max = 500) String resume,
                          @NotBlank String summary,
                          @NotNull @DecimalMin("20") Double price,
                          @NotNull @Min(100) Integer pagesNumber,
                          @NotBlank @UniqueValue(domainClass = Book.class, fieldName = "isbn") String isbn,
                          @NotNull @JsonFormat(pattern = "dd/MM/yyy", shape = STRING) @Future LocalDate publishDate,
                          @NotNull Long categoryId,
                          @NotNull Long authorId) {

    public Book toModel(EntityManager entityManager) {
        Category category = Optional.ofNullable(entityManager.find(Category.class, categoryId)).orElseThrow(() -> new ResourceNotFoundException("Category with id %s not found".formatted(categoryId)));
        Author author = Optional.ofNullable(entityManager.find(Author.class, authorId)).orElseThrow(() -> new ResourceNotFoundException("Author with id %s not found".formatted(authorId)));
        return new Book(title, resume, summary, BigDecimal.valueOf(price), pagesNumber, isbn, publishDate, category, author);
    }

}
