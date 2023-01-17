package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.book.Book;
import br.com.deveficiente.cdc.orderItem.OrderItem;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityById;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewOrderItemForm(@ExistsEntityById(domainClass = Book.class) Long bookId,
                               @NotNull @Positive Integer quantity) {

    public OrderItem toModel(EntityManager entityManager) {
        Book book = entityManager.find(Book.class, bookId);
        return new OrderItem(book, quantity);
    }
}
