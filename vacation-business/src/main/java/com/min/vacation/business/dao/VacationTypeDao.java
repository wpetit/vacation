/**
 * 
 */
package com.min.vacation.business.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.min.vacation.business.model.VacationType;

/**
 * The VacationTypeDao class.
 * 
 * @author wpetit
 */
@Repository
public class VacationTypeDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the given vacationType.
     * 
     * @param vacationType
     *            the vacationType to save.
     */
    public void save(final VacationType vacationType) {
        entityManager.persist(vacationType);
    }

    /**
     * Retrieve the user vacation types.
     * 
     * @param username
     *            the user username.
     * @return the vacation types.
     */
    public List<VacationType> getUserVacationType(final String username) {
        TypedQuery<VacationType> query = entityManager
                .createQuery(
                        "select vt from VacationType vt join vt.user u where u.username=:username",
                        VacationType.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

}
