package br.com.deveficiente.cdc.order;

import br.com.deveficiente.cdc.orderItem.OrderItemView;

import java.math.BigDecimal;
import java.util.List;

public record OrderView(Long purchaseId,
                        List<OrderItemView> items,
                        BigDecimal amount,
                        Boolean hasCoupon,
                        BigDecimal amountWithCoupon) {
    public OrderView(Order order) {
        this(order.getPurchaseId(), order.getOrderItems().stream().map(OrderItemView::new).toList(), order.getOrderAmountWithoutCouponDiscount(), order.hasDiscountCoupon(), order.getOrderAmountWithCupounDiscount());
    }
}
