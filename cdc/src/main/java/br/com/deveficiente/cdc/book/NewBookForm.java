package br.com.deveficiente.cdc.book;

import br.com.deveficiente.cdc.author.Author;
import br.com.deveficiente.cdc.category.Category;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityByField;
import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

// 3 points of cognitive load
public record NewBookForm(@NotBlank @UniqueValue(domainClass = Book.class, fieldName = "title") String title,
                          @NotBlank @Length(max = 500) String resume,
                          @NotBlank String summary,
                          @NotNull @DecimalMin("20") Double price,
                          @NotNull @Min(100) Integer pagesNumber,
                          @NotBlank @UniqueValue(domainClass = Book.class, fieldName = "isbn") String isbn,
                          @NotNull @JsonFormat(pattern = "dd/MM/yyy", shape = STRING) @Future LocalDate publishDate,
                          @ExistsEntityByField(domainClass = Category.class, field = "id") @NotNull Long categoryId,
                          @ExistsEntityByField(domainClass = Author.class, field = "id") @NotNull Long authorId) {

    public Book toModel(EntityManager entityManager) {
        Category category = entityManager.find(Category.class, categoryId);
        Author author = entityManager.find(Author.class, authorId);
        return new Book(title, resume, summary, BigDecimal.valueOf(price), pagesNumber, isbn, publishDate, category, author);
    }
}
