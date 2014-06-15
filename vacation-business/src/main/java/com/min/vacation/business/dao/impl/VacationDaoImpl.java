package com.min.vacation.business.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.min.vacation.business.dao.VacationDao;
import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.SortType;
import com.min.vacation.business.model.Vacation;

/**
 * The {@link VacationDaoImpl} class.
 * 
 * @author WPETIT
 */
@Repository
public class VacationDaoImpl implements VacationDao {

    @PersistenceContext
    private EntityManager entityManager;

    /** {@inheritDoc} */
    @Override
    public PaginatedModel<Vacation> findUserVacations(final String username,
            final int startIndex, final int pageSize,
            final String sortAttribute, final SortType sortType) {
        String sortField = sortAttribute;
        if (StringUtils.isEmpty(sortAttribute)) {
            sortField = "from";
        }
        String sort = "desc";
        if (sortType != null) {
            switch (sortType) {
            case ASC:
                sort = "asc";
                break;
            case DESC:
                sort = "desc";
                break;
            }
        }
        TypedQuery<Vacation> query = entityManager.createQuery(
                "select v from Vacation v join v.user u where u.username=:username order by v."
                        + sortField + " " + sort, Vacation.class);
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        query.setParameter("username", username);

        PaginatedModel<Vacation> paginatedModel = new PaginatedModel<Vacation>();
        paginatedModel.setStartIndex(startIndex);
        paginatedModel.setModelList(query.getResultList());
        // retrieve the total number of user vacations
        paginatedModel.setTotal(getUserVacationsCount(username));

        return paginatedModel;
    }

    /** {@inheritDoc} **/
    @Override
    public int getUserVacationsCount(final String username) {
        TypedQuery<Long> query = entityManager
                .createQuery(
                        "select count(v.id) from Vacation v join v.user u where u.username=:username",
                        Long.class);
        query.setParameter("username", username);
        return query.getSingleResult().intValue();
    }
}