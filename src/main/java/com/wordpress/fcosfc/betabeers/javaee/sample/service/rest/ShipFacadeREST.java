package com.wordpress.fcosfc.betabeers.javaee.sample.service.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
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
 * Ship entity REST facade
 * 
 * @author Paco
 */
@Stateless
@Path("ship")
public class ShipFacadeREST {
    
    @Inject
    protected ShipFacade shipFacade;

    public ShipFacadeREST() {
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Ship entity) {
        shipFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Ship entity) {
        shipFacade.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        shipFacade.remove(shipFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Ship find(@PathParam("id") Long id) {
        return shipFacade.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Ship> findAll() {
        return shipFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Ship> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return shipFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(shipFacade.count());
    }
    
}
