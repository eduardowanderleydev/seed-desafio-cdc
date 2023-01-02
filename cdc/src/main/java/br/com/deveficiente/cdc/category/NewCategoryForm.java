package br.com.deveficiente.cdc.category;

import br.com.deveficiente.cdc.shared.validation.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NewCategoryForm(@NotBlank @UniqueValue(domainClass = Category.class, fieldName = "name") String name) {
    public Category toCategory(){
        return new Category(name);
    }
}
