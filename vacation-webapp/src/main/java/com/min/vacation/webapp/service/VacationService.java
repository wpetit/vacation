package com.min.vacation.webapp.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
     * Find the vacation with the given id.
     * 
     * @param id
     *            the vacation id
     * @return the vacation found
     */
    @GET
    @Path("{id}")
    public Vacation getVacation(@PathParam("id") final int id) {
        return vacationBusiness.getVacation(id);
    }

    /**
     * Update the given vacation.
     * 
     * @param vacation
     *            the vacation
     */
    @POST
    @Path("{id}")
    public void updateVacation(final Vacation vacation) {
        User user = userBusiness.getUserByUsername(getAuthenticatedUsername());
        vacation.setUser(user);
        vacationBusiness.updateVacation(vacation);
    }

    /**
     * Delete the vacation with the id given.
     * 
     * @param id
     *            the vacation id
     */
    @DELETE
    @Path("{id}")
    public void deleteVacation(@PathParam("id") final int id) {
        vacationBusiness.deleteVacation(id);
    }

    /**
     * Create a vacation type for the user connected.
     * 
     * @param vacationType
     *            the vacation type
     */
    @POST
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
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacation(final Vacation vacation) {
        LOG.debug("Creating vacation : from {} to {} with type : {}", vacation
                .getFrom(), vacation.getTo(), vacation.getType().getId());
        User user = userBusiness.getUserByUsername(getAuthenticatedUsername());
        VacationType vacationType = vacationBusiness
                .getVacationTypeById(vacation.getType().getId());
        vacation.setType(vacationType);
        vacation.setUser(user);
        vacationBusiness.save(vacation);
    }

    /**
     * Return users vacation types.
     * 
     * @param sortAttribute
     *            the attribute to sort on.
     * @param sortType
     *            the sort operator.
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

    /**
     * Get the vacation type with the given id.
     * 
     * @param id
     *            the vacation type id.
     * @return the vacation type found or null if not found.
     */
    @GET
    @Path("type/{id}")
    public VacationType getVacationType(@PathParam("id") final int id) {
        VacationType vacationType = vacationBusiness.getVacationTypeById(id);
        LOG.debug("Vacation type found: {}", vacationType);
        return vacationType;
    }

    /**
     * Update the given vacationType.
     * 
     * @param vacationType
     *            the vacation type to update
     */
    @POST
    @Path("type/{id}")
    public void updateVacationType(final VacationType vacationType) {
        LOG.debug("VacationType to update: {}", vacationType);
        User user = userBusiness.getUserByUsername(getAuthenticatedUsername());
        vacationType.setUser(user);
        vacationBusiness.updateVacationType(vacationType);
    }

    /**
     * Delete the vacationType with the given id.
     * 
     * @param id
     *            the id
     */
    @DELETE
    @Path("type/{id}")
    public void deleteVacationType(@PathParam("id") final int id) {
        LOG.debug("VacationType id to delete: {}", id);
        vacationBusiness.deleteVacationType(id);
    }
}
