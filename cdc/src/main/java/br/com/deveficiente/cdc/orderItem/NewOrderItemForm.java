package br.com.deveficiente.cdc.orderItem;

import br.com.deveficiente.cdc.book.Book;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityByField;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewOrderItemForm(@ExistsEntityByField(domainClass = Book.class, field = "id") Long bookId,
                               @NotNull @Positive Integer quantity) {

    public OrderItem toModel(EntityManager entityManager) {
        Book book = entityManager.find(Book.class, bookId);
        return new OrderItem(book, quantity);
    }
}
