package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Country entity REST facade. It's a stateless EJB.
 * 
 * Fachada del recurso REST para la entidad Country.
 * 
 * @author Paco Saucedo
 */
@Stateless
@Path("country")
public class CountryFacadeREST {
    
    @Inject
    protected CountryFacade countryFacade;

    public CountryFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Country entity) {
        countryFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Country entity) {
        countryFacade.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        countryFacade.remove(countryFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Country find(@PathParam("id") String id) {
        return countryFacade.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Country> findAll() {
        return countryFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Country> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return countryFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(countryFacade.count());
    }
    
}