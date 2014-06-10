package com.min.vacation.business.model;

import java.util.List;

/**
 * The {@link PaginatedModel} class.
 * 
 * @author WPETIT
 * 
 * @param <T>
 *            the {@link Model} to paginate
 */
public class PaginatedModel<T extends Model> {
    /** The startIndex. */
    private int startIndex;
    /** The total. */
    private int total;
    /** The modelList. */
    private List<T> modelList;

    /**
     * Return the PaginatedModel startIndex.
     * 
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Set the PaginatedModel startIndex.
     * 
     * @param startIndex
     *            the startIndex to set
     */
    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Return the PaginatedModel total.
     * 
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Set the PaginatedModel total.
     * 
     * @param total
     *            the total to set
     */
    public void setTotal(final int total) {
        this.total = total;
    }

    /**
     * Return the PaginatedModel modelList.
     * 
     * @return the modelList
     */
    public List<T> getModelList() {
        return modelList;
    }

    /**
     * Set the PaginatedModel modelList.
     * 
     * @param modelList
     *            the modelList to set
     */
    public void setModelList(final List<T> modelList) {
        this.modelList = modelList;
    }
}
