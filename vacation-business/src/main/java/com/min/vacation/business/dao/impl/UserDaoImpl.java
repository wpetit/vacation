/**
 * 
 */
package com.min.vacation.business.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.min.vacation.business.dao.UserDao;
import com.min.vacation.business.model.User;

/**
 * The UserDaoImpl class.
 * 
 * @author wpetit
 */
@Repository
public class UserDaoImpl implements UserDao {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(UserDaoImpl.class);

    /** The entityManager. */
    @PersistenceContext
    private EntityManager entityManager;

    /** {@inheritDoc} */
    @Override
    public void save(final User user) {
        entityManager.persist(user);
    }

    /** {@inheritDoc} */
    @Override
    public User getUserByUsername(final String username) {
        User result = null;
        try {
            TypedQuery<User> query = entityManager
                    .createQuery(
                            "select u from User u where username=:username",
                            User.class);
            query.setParameter("username", username);
            result = query.getSingleResult();
        } catch (NoResultException e) {
            LOG.info("No user found for username : " + username, e);
        }
        return result;
    }
}
