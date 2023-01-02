package br.com.deveficiente.cdc.shared.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String dommainAttribute;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.dommainAttribute = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        List result = entityManager
                .createQuery("select 1 from %s where %s =:value".formatted(this.domainClass.getName(), this.dommainAttribute))
                .setParameter("value", o)
                .getResultList();

        Assert.state(result.size() <= 1, "More than one %s was found with the %s".formatted(this.domainClass.getName(), this.dommainAttribute));

        return result.isEmpty();
    }
}
