package com.min.vacation.webapp.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.webapp.dao.VacationDao;
import com.min.vacation.webapp.model.PaginatedModel;
import com.min.vacation.webapp.model.Vacation;

@Component
@Path("vacation")
public class VacationService {
    @Autowired
    private VacationDao vacationDao;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedModel<Vacation> getVacation(@QueryParam("startIndex") final int startIndex,
            @QueryParam("pageSize") final int pageSize,
            @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("ASC") @QueryParam("sortType") final String sortType) {
        return vacationDao.findAll(startIndex, pageSize, sortAttribute,
                ServiceUtils.convertSortType(sortType));
    }
}
