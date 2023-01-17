package br.com.deveficiente.cdc.order;

import br.com.deveficiente.cdc.orderItem.OrderItem;
import br.com.deveficiente.cdc.orderItem.NewOrderItemForm;
import br.com.deveficiente.cdc.purchase.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public record NewOrderForm(@NotNull @Positive BigDecimal amount,
                           @NotEmpty List<@Valid NewOrderItemForm> items) {
    public Function<Purchase, Order> toModel(EntityManager entityManager) {
        List<OrderItem> orderItems = items.stream().map(itemForm -> itemForm.toModel(entityManager)).toList();
        return (purchase) -> new Order(orderItems, purchase, amount);
    }
}
