package com.min.vacation.business.dao;

import java.util.List;

import com.min.vacation.business.model.VacationType;

public interface VacationTypeDao {

    /** {@inheritDoc} */
    public abstract void save(VacationType vacationType);

    /** {@inheritDoc} */
    public abstract List<VacationType> getUserVacationType(String username);

}
