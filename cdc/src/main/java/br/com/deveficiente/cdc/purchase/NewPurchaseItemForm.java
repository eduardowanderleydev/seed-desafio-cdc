package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.book.Book;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityById;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewPurchaseItemForm(@ExistsEntityById(domainClass = Book.class) Long bookId,
                                  @NotNull @Positive Integer quantity) {}
