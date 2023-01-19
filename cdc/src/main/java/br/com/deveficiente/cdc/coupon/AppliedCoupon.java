package br.com.deveficiente.cdc.coupon;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Embeddable
public class AppliedCoupon {

    @ManyToOne
    @NotNull
    private Coupon coupon;

    @Positive
    @NotNull
    private Integer couponDiscountPercentual;

    @NotNull
    @Future
    private LocalDate expirationDate;

    public AppliedCoupon(Coupon coupon) {
        this.coupon = coupon;
        this.couponDiscountPercentual = coupon.getDiscountPercentual();
        this.expirationDate = coupon.getExpirationDate();
    }

    /** Hibernate only */
    @Deprecated
    public AppliedCoupon() {}

    public Integer getCouponDiscountPercentual() {
        return couponDiscountPercentual;
    }

    public String getCouponCode() {
        return this.coupon.getCode();
    }
}
