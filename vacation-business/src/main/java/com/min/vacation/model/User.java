/**
 * 
 */
package com.min.vacation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The User class.
 * 
 * @author wpetit
 */
@Entity
@Table(name = "USERS")
public class User implements Model {
    /** The serialVersionUID. */
    private static final long serialVersionUID = 3001805545630803737L;
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /** The username. */
    private String username;

    /**
     * Return the User id.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the User id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Return the User username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the User username.
     * 
     * @param username
     *            the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }
}
