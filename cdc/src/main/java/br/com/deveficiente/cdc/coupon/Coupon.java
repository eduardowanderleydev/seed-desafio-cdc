package br.com.deveficiente.cdc.coupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDate.now;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String code;

    @Positive
    @NotNull
    @Column(nullable = false)
    private Integer discountPercentual;

    @Future
    private LocalDate expirationDate;

    @Deprecated
    public Coupon() {}

    public Coupon(String code, Integer discountPercentual, LocalDate expirationDate) {
        Assert.hasText(code, "Code cannot be null");
        Assert.notNull(discountPercentual, "Discount percentual cannot be null");
        Assert.isTrue(discountPercentual > 0, "Discount percentual must be positive");
        Assert.notNull(expirationDate, "Expiration date cannot be null");

        this.code = code;
        this.discountPercentual = discountPercentual;
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return this.expirationDate.isAfter(now());
    }

    public Integer getDiscountPercentual() {
        return discountPercentual;
    }
}
