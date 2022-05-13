package br.com.luciano.loja.validation;

import br.com.luciano.loja.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Transactional
@Repository
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, String> {

    @PersistenceContext
    private EntityManager em;
    private String columnName;
    private String className;

    @Override
    public void initialize(UniqueKey constraintAnnotation) {
        this.columnName = constraintAnnotation.columnName();
        this.className = constraintAnnotation.className();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        StringBuilder jpql =new StringBuilder();
        jpql.append("SELECT o FROM ")
                .append(className)
                .append(" o WHERE ")
                .append("o.")
                .append(this.columnName)
                .append(" = :")
                .append(this.columnName);

        Query query = em.createQuery(jpql.toString());
        query.setParameter(this.columnName, value);
        return query.getResultList().isEmpty();
    }
}
