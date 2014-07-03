/**
 * 
 */
package com.min.vacation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.min.vacation.dao.VacationTypeDao;
import com.min.vacation.model.SortType;
import com.min.vacation.model.VacationType;

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
    public List<VacationType> getUserVacationType(final String username,
            final String sortAttribute, final SortType sortType) {
        String sortField = sortAttribute;
        if (StringUtils.isEmpty(sortAttribute)) {
            sortField = "type";
        }
        String sort = DaoUtils.getStringSort(sortType, SortType.ASC);

        TypedQuery<VacationType> query = entityManager
                .createQuery(
                        "select vt from VacationType vt join vt.user u where u.username=:username order by vt."
                                + sortField + " " + sort, VacationType.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    /** {@inheritDoc} **/
    @Override
    public VacationType getVacationTypeById(final int id) {
        return entityManager.find(VacationType.class, id);
    }

}
