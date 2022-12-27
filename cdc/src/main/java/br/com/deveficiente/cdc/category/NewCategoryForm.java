package br.com.deveficiente.cdc.category;

import jakarta.validation.constraints.NotBlank;

public record NewCategoryForm(@NotBlank String name) {
    public Category toCategory(){
        return new Category(name);
    }
}
