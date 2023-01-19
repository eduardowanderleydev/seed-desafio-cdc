package br.com.deveficiente.cdc.orderItem;

import java.math.BigDecimal;

public record OrderItemView(String bookTitle,
                            BigDecimal price,
                            Integer quantity) {
    public OrderItemView(OrderItem orderItem) {
        this(orderItem.getBookName(), orderItem.getPriceAtMoment(), orderItem.getQuantity());
    }
}
