/**
 * 
 */
package com.min.vacation.business.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.min.vacation.business.dao.VacationTypeDao;
import com.min.vacation.business.model.VacationType;

/**
 * The {@link VacationTypeDaoImpl} class.
 * 
 * @author WPETIT
 */
@Repository
public class VacationTypeDaoImpl implements VacationTypeDao {

    /** The entityManager. */
    @PersistenceContext
    private EntityManager entityManager;

    /** {@inheritDoc} */
    @Override
    public void save(final VacationType vacationType) {
        entityManager.persist(vacationType);
    }

    /** {@inheritDoc} */
    @Override
    public List<VacationType> getUserVacationType(final String username) {
        TypedQuery<VacationType> query = entityManager
                .createQuery(
                        "select vt from VacationType vt join vt.user u where u.username=:username",
                        VacationType.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    /** {@inheritDoc} **/
    @Override
    public VacationType getVacationTypeById(final int id) {
        return entityManager.find(VacationType.class, id);
    }

}
