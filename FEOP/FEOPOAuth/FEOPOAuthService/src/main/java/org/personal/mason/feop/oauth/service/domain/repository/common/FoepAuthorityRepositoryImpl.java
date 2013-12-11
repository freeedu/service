package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FoepAuthorityRepositoryImpl implements FoepAuthorityRepositoryCustom {
    private final static String DEFAULT_USER_ROLE = "ROLE_USER";

    @PersistenceContext(unitName = "oauth2")
    @Qualifier(value = "entityManagerFactory")
    private EntityManager em;

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<FoepAuthority> getDefaultUserRoles(Object... roleNames) {
        try {
            if (roleNames.length == 0) {
                roleNames = new Object[]{DEFAULT_USER_ROLE};
            }

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<FoepAuthority> criteria = criteriaBuilder.createQuery(FoepAuthority.class);
            Root<FoepAuthority> root = criteria.from(FoepAuthority.class);
            Predicate roleNamePredicate = root.get("name").in(roleNames);
            Predicate enablePredicate = criteriaBuilder.equal(root.get("enabled"), true);
            Predicate wherePredicate = criteriaBuilder.and(roleNamePredicate, enablePredicate);
            criteria.distinct(true).select(root).where(wherePredicate);
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
