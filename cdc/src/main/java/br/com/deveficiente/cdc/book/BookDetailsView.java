package br.com.deveficiente.cdc.book;

import br.com.deveficiente.cdc.author.AuthorDetailsView;

import java.math.BigDecimal;

public record BookDetailsView(String title,
                              String resume,
                              String summary,
                              BigDecimal price,
                              Integer pagesNumber,
                              String isbn,
                              String publishDate,
                              AuthorDetailsView author) {
    public BookDetailsView(String title, String resume, String summary, BigDecimal price, Integer pagesNumber, String isbn, String publishDate, AuthorDetailsView author) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.author = author;
    }

    public BookDetailsView(Book book) {
        this(book.getTitle(), book.getResume(), book.getSummary(), book.getPrice(), book.getPagesNumber(), book.getIsbn(), book.getPublishDate().toString(), new AuthorDetailsView(book.getAuthorName(), book.getAuthorDescription()));
    }
}
