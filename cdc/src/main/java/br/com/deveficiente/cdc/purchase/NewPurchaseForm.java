package br.com.deveficiente.cdc.purchase;

import br.com.deveficiente.cdc.shared.validation.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewPurchaseForm(@NotBlank @Email String email,
                              @NotBlank String name,
                              @NotBlank String lastName,
                              @NotBlank @Document String document,
                              @NotBlank String address,
                              @NotBlank String addressComplement,
                              @NotBlank String city,
                              @NotBlank String cep,
                              @NotNull Long countryId,
                              @NotNull Long stateId,
                              @NotBlank String phone) {}
