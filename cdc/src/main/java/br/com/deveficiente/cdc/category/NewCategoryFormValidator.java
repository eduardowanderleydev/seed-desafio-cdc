package br.com.deveficiente.cdc.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NewCategoryFormValidator implements Validator {

    private CategoryRepository categoryRepository;

    public NewCategoryFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(NewCategoryForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewCategoryForm form = (NewCategoryForm) target;

        Optional<Category> possibleCategory = categoryRepository.findByName(form.name());

        if (possibleCategory.isPresent()) {
            errors.rejectValue("name", null, "Category with name " + form.name() + " already exists");
        }
    }
}
