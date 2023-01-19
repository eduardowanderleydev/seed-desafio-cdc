package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.order.OrderView;

public record PurchaseDetailView(String email,
                                 String name,
                                 String lastName,
                                 String document,
                                 String address,
                                 String addressComplement,
                                 String city,
                                 String cep,
                                 String country,
                                 String state,
                                 String phone,
                                 OrderView order,
                                 String coupon) {
    public PurchaseDetailView(Purchase purchase) {
        this(purchase.getEmail(), purchase.getName(), purchase.getLastName(), purchase.getDocument(),
                purchase.getAddress(), purchase.getAddressComplement(), purchase.getCity(), purchase.getCep(),
                purchase.getCountryName(), purchase.getStateName(), purchase.getPhone(), new OrderView(purchase.getPurchaseOrder()), purchase.getAppliedCouponCode());
    }
}
