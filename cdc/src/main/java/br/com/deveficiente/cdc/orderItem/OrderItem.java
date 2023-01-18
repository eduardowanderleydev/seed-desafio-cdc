package br.com.deveficiente.cdc.orderItem;

import br.com.deveficiente.cdc.book.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Book book;

    @Positive
    private BigDecimal priceAtMoment;

    @NotNull
    private Integer quantity;

    public OrderItem(@NotNull Book book, @NotNull Integer quantity) {
        Assert.notNull(book, "Book cannot be null");
        Assert.notNull(quantity, "Quantity cannot be null");

        this.book = book;
        this.quantity = quantity;
        this.priceAtMoment = book.getPrice();
    }

    /** Hibernate only */
    @Deprecated
    public OrderItem() {}

    public BigDecimal getItemPrice() {
        return priceAtMoment.multiply(new BigDecimal(quantity));
    }
}
