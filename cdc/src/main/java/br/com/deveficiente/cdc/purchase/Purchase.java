package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.coupon.AppliedCoupon;
import br.com.deveficiente.cdc.coupon.Coupon;
import br.com.deveficiente.cdc.order.Order;
import br.com.deveficiente.cdc.shared.validation.Document;
import br.com.deveficiente.cdc.state.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.function.Function;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.nonNull;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String lastName;
    private String document;
    private String address;
    private String addressComplement;
    private String city;
    private String cep;
    private String phone;

    @ManyToOne
    private Country country;

    @ManyToOne
    private State state;

    @OneToOne(mappedBy = "purchase", cascade = PERSIST)
    private Order purchaseOrder;

    @Embedded
    private AppliedCoupon appliedCoupon;

    public Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName, @NotBlank @Document String document, @NotBlank String address, @NotBlank String addressComplement, @NotBlank String city, @NotBlank String cep, Country country, @NotBlank String phone, Coupon coupon, Function<Purchase, Order> createOrderFunction) {
        Assert.hasText(email, "Email cannot be empty");
        Assert.hasText(name, "Name cannot be empty");
        Assert.hasText(lastName, "Last name cannot be empty");
        Assert.hasText(document, "Document name cannot be empty");
        Assert.hasText(address, "Address name cannot be empty");
        Assert.hasText(addressComplement, "Address complement name cannot be empty");
        Assert.hasText(city, "City name cannot be empty");
        Assert.hasText(cep, "CEP name cannot be empty");
        Assert.notNull(country, "Country cannot be null");
        Assert.hasText(phone, "Phone name cannot be empty");

        if (nonNull(coupon)) applyCoupon(coupon);
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.cep = cep;
        this.country = country;
        this.phone = phone;
        this.purchaseOrder = createOrderFunction.apply(this);
    }

    @Deprecated
    public Purchase() {}

    public void setState(State state) {
        Assert.notNull(state, "State cannot be null");
        Assert.state(state.belongsTo(country), "State doesnt belong to country");
        this.state = state;
    }

    public boolean hasDiscountCoupon() {
        return this.appliedCoupon != null;
    }

    public BigDecimal getDiscountInPercentage() {
        if (!hasDiscountCoupon()) return ZERO;
        return BigDecimal.valueOf((double) this.appliedCoupon.getCouponDiscountPercentual() / 100);
    }

    public void applyCoupon(Coupon coupon) {
        Assert.notNull(coupon, "Coupon cannot be null");
        Assert.isTrue(coupon.isValid(), "Coupon not valid");
        Assert.isNull(appliedCoupon, "[BUG] Cannot change a coupon");
        this.appliedCoupon = new AppliedCoupon(coupon);
    }
}
