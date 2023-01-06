package br.com.deveficiente.cdc.book;

import java.math.BigDecimal;

public record BookDetailsView(String title,
                              String resume,
                              String summary,
                              BigDecimal price,
                              Integer pagesNumber,
                              String isbn,
                              String publishDate,
                              Long categoryId,
                              String authorName) {
    public BookDetailsView(String title, String resume, String summary, BigDecimal price, Integer pagesNumber, String isbn, String publishDate, Long categoryId, String authorName) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.authorName = authorName;
    }

    public BookDetailsView(Book book) {
        this(book.getTitle(), book.getResume(), book.getSummary(), book.getPrice(), book.getPagesNumber(), book.getIsbn(), book.getPublishDate().toString(), book.getCategoryId(), book.getAuthorName());
    }
}
