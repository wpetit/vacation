/**
 * 
 */
package com.min.vacation.business.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
