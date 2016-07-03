package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Abstract REST facade.
 * 
 * Fachada abstracta REST.
 * 
 * @author Paco Saucedo
 * @param <T> 
 */
public abstract class CrudRestFacade<T> {
    
    public abstract CrudFacade<T> getCrudFacade();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(T entity) {
        getCrudFacade().create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") String id, T entity) {
        getCrudFacade().update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        getCrudFacade().remove(getCrudFacade().find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T find(@PathParam("id") String id) {
        return getCrudFacade().find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findAll() {
        return getCrudFacade().findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return getCrudFacade().findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(getCrudFacade().count());
    }
    
}
