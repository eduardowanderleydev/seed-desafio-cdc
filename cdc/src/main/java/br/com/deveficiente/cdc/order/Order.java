package br.com.deveficiente.cdc.order;

import br.com.deveficiente.cdc.orderItem.OrderItem;
import br.com.deveficiente.cdc.purchase.Purchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

@Entity
@Table(name = "PurchaseOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(cascade = ALL)
    private List<OrderItem> orderItems;

    @OneToOne
    private Purchase purchase;

    @Positive
    private BigDecimal amount;

    public Order(@NotEmpty List<OrderItem> orderItems, @NotNull Purchase purchase, @NotNull @Positive BigDecimal amount) {
        Assert.isTrue(!orderItems.isEmpty(), "Must have order items");
        Assert.notNull(purchase, "Purchase cannot be null");
        Assert.notNull(amount, "Amount cannot be null");

        this.amount = amount;
        this.orderItems = orderItems;
        this.purchase = purchase;
    }

    @Deprecated
    public Order() {}

    public boolean hasValidAmount() {
        BigDecimal amount = orderItems.stream().map(OrderItem::getItemPrice).reduce(ZERO, BigDecimal::add);

        if (purchase.hasDiscountCoupon()) {
            amount = amount.subtract((amount.multiply(purchase.getDiscountInPercentage())));
        }

        return amount.setScale(2, HALF_EVEN)
                .equals(this.amount.setScale(2, HALF_EVEN));
    }
}
