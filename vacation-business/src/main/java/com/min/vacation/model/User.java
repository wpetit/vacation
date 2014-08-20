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
    /** The password. */
    private String password;
    /** The role. */
    private String role;

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

    /**
     * Return the User password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the User password.
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Return the User role.
     * 
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the User role.
     * 
     * @param role
     *            the role to set
     */
    public void setRole(final String role) {
        this.role = role;
    }
}
