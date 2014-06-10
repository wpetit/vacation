/**
 * 
 */
package com.min.vacation.business.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.min.vacation.business.model.User;

/**
 * The UserDao class.
 * 
 * @author wpetit
 */
@Repository
public class UserDao {

    /** The entityManager. */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the given user.
     * 
     * @param user
     *            the user to save.
     */
    public void save(final User user) {
        entityManager.persist(user);
    }

    /**
     * Retrieve the user with the given username.
     * 
     * @param username
     *            the username
     * @return the user
     */
    public User getUser(final String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where username=:username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
