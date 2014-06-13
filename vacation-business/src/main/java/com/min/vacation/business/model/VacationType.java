/**
 * 
 */
package com.min.vacation.business.model;

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
    /** The beginDay. */
    private int beginDay;
    /** The beginMonth. */
    private int beginMonth;
    /** The endDay. */
    private int endDay;
    /** The endMonth. */
    private int endMonth;
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
     * Return the VacationType beginDay.
     * 
     * @return the beginDay
     */
    public int getBeginDay() {
        return beginDay;
    }

    /**
     * Set the VacationType beginDay.
     * 
     * @param beginDay
     *            the beginDay to set
     */
    public void setBeginDay(final int beginDay) {
        this.beginDay = beginDay;
    }

    /**
     * Return the VacationType beginMonth.
     * 
     * @return the beginMonth
     */
    public int getBeginMonth() {
        return beginMonth;
    }

    /**
     * Set the VacationType beginMonth.
     * 
     * @param beginMonth
     *            the beginMonth to set
     */
    public void setBeginMonth(final int beginMonth) {
        this.beginMonth = beginMonth;
    }

    /**
     * Return the VacationType endDay.
     * 
     * @return the endDay
     */
    public int getEndDay() {
        return endDay;
    }

    /**
     * Set the VacationType endDay.
     * 
     * @param endDay
     *            the endDay to set
     */
    public void setEndDay(final int endDay) {
        this.endDay = endDay;
    }

    /**
     * Return the VacationType endMonth.
     * 
     * @return the endMonth
     */
    public int getEndMonth() {
        return endMonth;
    }

    /**
     * Set the VacationType endMonth.
     * 
     * @param endMonth
     *            the endMonth to set
     */
    public void setEndMonth(final int endMonth) {
        this.endMonth = endMonth;
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
