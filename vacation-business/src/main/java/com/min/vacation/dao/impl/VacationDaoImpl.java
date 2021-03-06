package com.min.vacation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.min.vacation.dao.VacationDao;
import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.SortType;
import com.min.vacation.model.Vacation;

/**
 * The {@link VacationDaoImpl} class.
 * 
 * @author WPETIT
 */
@Repository
public class VacationDaoImpl implements VacationDao {

    /** The TYPE_ID_PARAM. */
    private static final String TYPE_ID_PARAM = "vacationTypeId";

    /** The USERNAME_PARAM. */
    private static final String USERNAME_PARAM = "username";

    /** The entityManager. */
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
        String sort = DaoUtils.getStringSort(sortType, SortType.DESC);
        TypedQuery<Vacation> query = entityManager.createQuery(
                "select v from Vacation v " + "join v.user u "
                        + "where u.username=:username order by v." + sortField
                        + " " + sort, Vacation.class);
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);
        query.setParameter(USERNAME_PARAM, username);

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
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(v.id) from Vacation v "
                        + "join v.user u where u.username=:username",
                Long.class);
        query.setParameter(USERNAME_PARAM, username);
        return query.getSingleResult().intValue();
    }

    /** {@inheritDoc} **/
    @Override
    public void save(final Vacation vacation) {
        entityManager.persist(vacation);
    }

    /** {@inheritDoc} **/
    @Override
    public List<Vacation> getVacationByUsernameAndType(final String username,
            final int vacationTypeId) {
        TypedQuery<Vacation> query = entityManager.createQuery(
                "select v from Vacation v "
                        + "where v.type.id=:vacationTypeId "
                        + "and v.user.username=:username", Vacation.class);
        query.setParameter(USERNAME_PARAM, username);
        query.setParameter(TYPE_ID_PARAM, vacationTypeId);
        return query.getResultList();
    }

    /** {@inheritDoc} **/
    @Override
    public void deleteVacationByVacationType(final int id) {
        Query q = entityManager
                .createQuery("delete from Vacation v where v.type.id=:vacationTypeId");
        q.setParameter(TYPE_ID_PARAM, id);
        q.executeUpdate();
    }

    /** {@inheritDoc} **/
    @Override
    public Vacation getVacationById(final int id) {
        return entityManager.find(Vacation.class, id);
    }

    /** {@inheritDoc} **/
    @Override
    public void update(final Vacation vacation) {
        entityManager.merge(vacation);
    }

    /** {@inheritDoc} **/
    @Override
    public void delete(final Vacation vacation) {
        entityManager.remove(vacation);
    }

}
