package br.com.deveficiente.cdc.shared.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsEntityByIdValidator implements ConstraintValidator<ExistsEntityById, Object> {

    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsEntityById constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) return true;

        List result = entityManager
                .createQuery("select 1 from %s where id =:value".formatted(this.domainClass.getName()))
                .setParameter("value", o)
                .getResultList();

        Assert.state(result.size() <= 1, "More than one %s was found with the same id".formatted(this.domainClass.getName()));

        return !result.isEmpty();
    }
}
