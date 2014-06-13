package com.min.vacation.webapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.min.vacation.business.dao.UserDao;
import com.min.vacation.business.dao.VacationDao;
import com.min.vacation.business.dao.VacationTypeDao;
import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.User;
import com.min.vacation.business.model.Vacation;
import com.min.vacation.business.model.VacationType;

@Component
@Path("vacation")
public class VacationService {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(AuthenticationService.class);

    /** The userDao. */
    @Autowired
    private UserDao userDao;

    /** The vacationDao. */
    @Autowired
    private VacationDao vacationDao;

    /** The vacationTypeDao. */
    @Autowired
    private VacationTypeDao vacationTypeDao;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedModel<Vacation> getVacation(
            @QueryParam("startIndex") final int startIndex,
            @QueryParam("pageSize") final int pageSize,
            @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("ASC") @QueryParam("sortType") final String sortType) {
        LOG.debug("Retrieving {} vacations from {} with sorting : {} {}",
                pageSize, startIndex, sortAttribute, sortType);
        return vacationDao.findAll(startIndex, pageSize, sortAttribute,
                ServiceUtils.convertSortType(sortType));
    }

    @POST
    @Path("type")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacationType(final VacationType vacationType) {
        LOG.debug("Creating vacation type : {}", vacationType);
        User user = userDao
                .getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal())
                        .getUsername());
        vacationType.setUser(user);
        vacationTypeDao.save(vacationType);
    }

    @GET
    @Path("type")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacationType> getVacationTypes() {
        LOG.debug("Getting vacation types");
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUsername();

        return vacationTypeDao.getUserVacationType(username);
    }
}
