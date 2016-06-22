package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
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
 * ShipType entity REST facade. It's a stateless EJB.
 * 
 * Fachada del recurso REST para la entidad ShipType.
 * 
 * @author Paco
 */
@Stateless
@Path("shiptype")
public class ShipTypeFacadeREST {
    
    @Inject
    protected ShipTypeFacade shipTypeFacade;

    public ShipTypeFacadeREST() {
    }

    @POST
    @Consumes({"application/json"})
    public void create(ShipType entity) {
        shipTypeFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") String id, ShipType entity) {
        shipTypeFacade.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        shipTypeFacade.remove(shipTypeFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ShipType find(@PathParam("id") String id) {
        return shipTypeFacade.find(id);
    }

    @GET
    @Produces({"application/json"})
    public List<ShipType> findAll() {
        return shipTypeFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<ShipType> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return shipTypeFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(shipTypeFacade.count());
    }
    
}
