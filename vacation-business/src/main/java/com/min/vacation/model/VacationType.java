/**
 * 
 */
package com.min.vacation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The VacationType class.
 * 
 * @author wpetit
 */
@Entity
@Table(name = "VACATION_TYPE")
public class VacationType implements Model {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -1619547149373155350L;
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /** The user. */
    @ManyToOne
    private User user;
    /** The type. */
    private String type;
    /** The beginDate. */
    private Date beginDate;
    /** The endDate. */
    private Date endDate;
    /** The numberOfDays. */
    private int numberOfDays;

    /**
     * Return the VacationType id.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the VacationType id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Return the VacationType user.
     * 
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the VacationType user.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Return the VacationType type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the VacationType type.
     * 
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Return the VacationType beginDate.
     * 
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Set the VacationType beginDate.
     * 
     * @param beginDate
     *            the beginDate to set
     */
    public void setBeginDate(final Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Return the VacationType endDate.
     * 
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the VacationType endDate.
     * 
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Return the VacationType numberOfDays.
     * 
     * @return the numberOfDays
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * Set the VacationType numberOfDays.
     * 
     * @param numberOfDays
     *            the numberOfDays to set
     */
    public void setNumberOfDays(final int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
