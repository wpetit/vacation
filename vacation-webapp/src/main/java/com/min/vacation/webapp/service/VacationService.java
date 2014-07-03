package com.min.vacation.webapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.vacation.business.UserBusiness;
import com.min.vacation.business.VacationBusiness;
import com.min.vacation.model.PaginatedModel;
import com.min.vacation.model.User;
import com.min.vacation.model.Vacation;
import com.min.vacation.model.VacationType;

/**
 * The {@link VacationService} class.
 * 
 * @author wpetit
 */
@Component
@Path("vacation")
public class VacationService extends AuthenticatedService {

    /** The LOG. */
    private static final Logger LOG = LoggerFactory
            .getLogger(VacationService.class);

    /** The userBusiness. */
    @Autowired
    private UserBusiness userBusiness;

    /** The vacationBusiness. */
    @Autowired
    private VacationBusiness vacationBusiness;

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
    public PaginatedModel<Vacation> getVacation(
            @QueryParam("startIndex") final int startIndex,
            @QueryParam("pageSize") final int pageSize,
            @DefaultValue("from") @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("desc") @QueryParam("sortType") final String sortType) {
        LOG.debug("Retrieving {} vacations from {} with sorting : {} {}",
                pageSize, startIndex, sortAttribute, sortType);
        String username = getAuthenticatedUsername();
        return vacationBusiness
                .findUserVacations(username, startIndex, pageSize,
                        sortAttribute, ServiceUtils.convertSortType(sortType));
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
        User user = userBusiness.getUserByUsername(getAuthenticatedUsername());
        vacationType.setUser(user);
        vacationBusiness.save(vacationType);
    }

    /**
     * Create a vacation for the user connected.
     * 
     * @param vacation
     *            the vacation
     * @param vacationTypeId
     *            the vacationType id
     */
    @POST
    @Path("{typeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacation(final Vacation vacation,
            @PathParam("typeId") final int vacationTypeId) {
        LOG.debug("Creating vacation : from {} to {} with type : {}",
                vacation.getFrom(), vacation.getTo(), vacationTypeId);
        User user = userBusiness.getUserByUsername(getAuthenticatedUsername());
        VacationType vacationType = vacationBusiness
                .getVacationTypeById(vacationTypeId);
        vacation.setType(vacationType);
        vacation.setUser(user);
        vacationBusiness.save(vacation);
    }

    /**
     * Return users vacation types.
     * 
     * @return vacation types.
     */
    @GET
    @Path("type")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacationType> getVacationTypes(
            @DefaultValue("type") @QueryParam("sortAttribute") final String sortAttribute,
            @DefaultValue("asc") @QueryParam("sortType") final String sortType) {
        LOG.debug("Getting vacation types");
        return vacationBusiness.getUserVacationType(getAuthenticatedUsername(),
                sortAttribute, ServiceUtils.convertSortType(sortType));
    }

    /**
     * Returns the number of vacations for the given type.
     * 
     * @param typeId
     *            the type id
     * @return the number of vacations
     */
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getNumberOfVacationsByType(@QueryParam("typeId") final int typeId) {
        LOG.debug("Getting number of vacations for type id : {}", typeId);
        return vacationBusiness.getVacationWorkingDaysCount(
                getAuthenticatedUsername(), typeId);
    }
}
