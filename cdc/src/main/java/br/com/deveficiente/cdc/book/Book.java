package br.com.deveficiente.cdc.book;

import br.com.deveficiente.cdc.author.Author;
import br.com.deveficiente.cdc.category.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 500)
    private String resume;

    private String summary;

    @Column(nullable = false)
    @DecimalMin("20")
    private BigDecimal price;

    @Column(nullable = false)
    @Min(100)
    private Integer pagesNumber;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Future
    @JsonFormat(pattern = "dd/MM/yyy", shape = STRING)
    private LocalDate publishDate;

    @ManyToOne
    @NotNull
    private Category category;

    @ManyToOne
    @NotNull
    private Author author;

    @Deprecated
    public Book() {}

    public Book(@NotBlank String title,
                @NotBlank @Length(max = 500) String resume,
                @NotBlank String summary,
                @DecimalMin("20") BigDecimal price,
                @Min(100) Integer pagesNumber,
                @NotBlank String isbn,
                @NotNull LocalDate publishDate,
                @NotNull Category category,
                @NotNull Author author) {
        Assert.hasText(title, "Title cannot be empty");
        Assert.hasText(resume, "Resume cannot be empty");
        Assert.hasText(summary, "Summary cannot be empty");
        Assert.notNull(price, "Price cannot be null");
        Assert.notNull(pagesNumber, "Pages number cannot be null");
        Assert.hasText(isbn, "ISBN cannot be empty");
        Assert.notNull(publishDate, "Publish date cannot be null");
        Assert.notNull(category, "Category cannot be null");
        Assert.notNull(publishDate, "Author cannot be null");

        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Long getCategoryId() {
        return category.getId();
    }

    public String getAuthorName() {
        return author.getName();
    }
}
