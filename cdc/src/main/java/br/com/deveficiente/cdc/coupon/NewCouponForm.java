package br.com.deveficiente.cdc.coupon;

import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record NewCouponForm(@UniqueValue(domainClass = Coupon.class, fieldName = "code") @NotNull String code,
                            @NotNull @Positive Integer discountPercentual,
                            @NotNull @Future @JsonFormat(pattern = "dd/MM/yyy", shape = STRING) LocalDate expirationDate) {

    public Coupon toModel() {
        return new Coupon(code, discountPercentual, expirationDate);
    }
}
