package com.min.vacation.webapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.business.dao.UserDao;
import com.min.vacation.business.dao.VacationDao;
import com.min.vacation.business.dao.VacationTypeDao;
import com.min.vacation.business.model.PaginatedModel;
import com.min.vacation.business.model.User;
import com.min.vacation.business.model.Vacation;
import com.min.vacation.business.model.VacationType;

/**
 * The {@link VacationService} class.
 * 
 * @author wpetit
 */
@Component
@Path("vacation")
public class VacationService extends AuthenticatedService {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(VacationService.class);

    /** The userDao. */
    @Autowired
    private UserDao userDao;

    /** The vacationDao. */
    @Autowired
    private VacationDao vacationDao;

    /** The vacationTypeDao. */
    @Autowired
    private VacationTypeDao vacationTypeDao;

    /**
     * Return user connected vacations.
     * 
     * @param startIndex
     *            the first vacation index to retrieve.
     * @param pageSize
     *            the number of vacation per page.
     * @param sortAttribute
     *            the attribute to sort on.
     * @param sortType
     *            the sort type to apply.
     * @return user vacations
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedModel<Vacation> getVacation(@QueryParam("startIndex") final int startIndex,
            @QueryParam("pageSize") final int pageSize,
            @DefaultValue("from") @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("asc") @QueryParam("sortType") final String sortType) {
        LOG.debug("Retrieving {} vacations from {} with sorting : {} {}", pageSize, startIndex,
                sortAttribute, sortType);
        String username = getAuthenticatedUsername();
        return vacationDao.findUserVacations(username, startIndex, pageSize, sortAttribute,
                ServiceUtils.convertSortType(sortType));
    }

    /**
     * Create a vacation type for the user connected.
     * 
     * @param vacationType
     *            the vacation type
     */
    @PUT
    @Path("type")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacationType(final VacationType vacationType) {
        LOG.debug("Creating vacation type : {}", vacationType);
        User user = userDao.getUserByUsername(getAuthenticatedUsername());
        vacationType.setUser(user);
        vacationTypeDao.save(vacationType);
    }

    /**
     * Return users vacation types.
     * 
     * @return vacation types.
     */
    @GET
    @Path("type")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacationType> getVacationTypes() {
        LOG.debug("Getting vacation types");
        return vacationTypeDao.getUserVacationType(getAuthenticatedUsername());
    }
}
