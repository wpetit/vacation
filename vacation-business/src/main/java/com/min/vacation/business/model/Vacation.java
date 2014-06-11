package com.min.vacation.business.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@link Vacation} class.
 * 
 * @author WPETIT
 * 
 */
@Entity
@Table(name = "VACATION")
public class Vacation implements Model {
    /** The serialVersionUID. */
    private static final long serialVersionUID = -2538872225808478363L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /** The type. */
    private String type;
    /** The from. */
    private Date from;
    /** The to. */
    private Date to;

    /**
     * Return the Vacation id.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the Vacation id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Return the Vacation type.
     * 
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the Vacation type.
     * 
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Return the Vacation from.
     * 
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Set the Vacation from.
     * 
     * @param from
     *            the from to set
     */
    public void setFrom(final Date from) {
        this.from = from;
    }

    /**
     * Return the Vacation to.
     * 
     * @return the to
     */
    public Date getTo() {
        return to;
    }

    /**
     * Set the Vacation to.
     * 
     * @param to
     *            the to to set
     */
    public void setTo(final Date to) {
        this.to = to;
    }

}
