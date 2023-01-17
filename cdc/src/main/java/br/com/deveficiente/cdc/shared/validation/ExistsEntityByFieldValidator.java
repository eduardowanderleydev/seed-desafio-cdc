package br.com.deveficiente.cdc.shared.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsEntityByFieldValidator implements ConstraintValidator<ExistsEntityByField, Object> {

    private Class<?> domainClass;
    private String fieldName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsEntityByField constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) return true;

        List result = entityManager
                .createQuery("select 1 from %s where %s =:value".formatted(this.domainClass.getName(), this.fieldName))
                .setParameter("value", o)
                .getResultList();

        Assert.state(result.size() <= 1, "More than one %s was found with the same id".formatted(this.domainClass.getName()));

        return !result.isEmpty();
    }
}
