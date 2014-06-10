package com.min.vacation.webapp.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.webapp.dao.VacationDao;
import com.min.vacation.webapp.model.PaginatedModel;
import com.min.vacation.webapp.model.Vacation;

@Component
@Path("vacation")
public class VacationService {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private VacationDao vacationDao;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedModel<Vacation> getVacation(@QueryParam("startIndex") final int startIndex,
            @QueryParam("pageSize") final int pageSize,
            @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("ASC") @QueryParam("sortType") final String sortType) {
        LOG.debug("Retrieving {} vacations from {} with sorting : {} {}", pageSize, startIndex,
                sortAttribute, sortType);
        return vacationDao.findAll(startIndex, pageSize, sortAttribute,
                ServiceUtils.convertSortType(sortType));
    }
}
