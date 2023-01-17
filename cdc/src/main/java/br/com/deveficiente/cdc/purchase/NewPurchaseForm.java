package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.country.Country;
import br.com.deveficiente.cdc.coupon.Coupon;
import br.com.deveficiente.cdc.order.NewOrderForm;
import br.com.deveficiente.cdc.order.Order;
import br.com.deveficiente.cdc.shared.validation.Document;
import br.com.deveficiente.cdc.shared.validation.ExistsEntityByField;
import br.com.deveficiente.cdc.state.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.function.Function;

import static java.util.Objects.nonNull;

public class NewPurchaseForm {

    private @NotBlank @Email String email;
    private @NotBlank String name;
    private @NotBlank String lastName;
    private @NotBlank @Document String document;
    private @NotBlank String address;
    private @NotBlank String addressComplement;
    private @NotBlank String city;
    private @NotBlank String cep;
    @ExistsEntityByField(domainClass = Country.class, field = "id")
    private @NotNull Long countryId;
    @ExistsEntityByField(domainClass = State.class, field = "id")
    private Long stateId;
    private @NotBlank String phone;
    private @Valid NewOrderForm orderForm;
    private String coupon;

    public NewPurchaseForm(@NotBlank @Email String email,
                           @NotBlank String name,
                           @NotBlank String lastName,
                           @NotBlank @Document String document,
                           @NotBlank String address,
                           @NotBlank String addressComplement,
                           @NotBlank String city,
                           @NotBlank String cep,
                           @NotNull Long countryId,
                           Long stateId,
                           @NotBlank String phone,
                           @Valid NewOrderForm orderForm) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.cep = cep;
        this.countryId = countryId;
        this.stateId = stateId;
        this.phone = phone;
        this.orderForm = orderForm;
    }

    public Purchase toModel(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, countryId);

        Function<Purchase, Order> createOrderFunction = orderForm.toModel(entityManager);

        Purchase purchase = new Purchase(email, name, lastName, document, address, addressComplement, city, cep, country, phone, createOrderFunction);

        Coupon coupon = entityManager.find(Coupon.class, this.coupon);
        purchase.setCoupon(coupon);

        if (nonNull(stateId)) {
            State state = entityManager.find(State.class, stateId);
            purchase.setState(state);
        }

        return purchase;
    }

    public boolean hasState() {
        return nonNull(stateId);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public String getCity() {
        return city;
    }

    public String getCep() {
        return cep;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public String getPhone() {
        return phone;
    }

    public NewOrderForm getOrderForm() {
        return orderForm;
    }
}
