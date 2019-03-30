package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.RestEntity;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * Abstract REST resource.
 *
 * Recurso REST abstracto.
 *
 * @author Paco Saucedo
 * @param <T>
 */
public abstract class CrudRestResource<T extends RestEntity> {

    public abstract CrudFacade<T> getCrudFacade();
    
    public abstract Logger getLogger();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(T entity, @Context UriInfo uriInfo) {
        try {
            entity = getCrudFacade().create(entity);

            return Response.created(
                    getURI(uriInfo,
                            entity.getId()))
                    .build();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") String id, T entity) {
        try {
            getCrudFacade().update(entity);

            return Response.ok().build();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        try {
            getCrudFacade().remove(getCrudFacade().find(id));

            return Response.noContent().build();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") String id) {
        try {
            T entity = getCrudFacade().find(id);

            return Response.ok(entity).build();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<T> entityList = getCrudFacade().findAll();

            return Response.ok(entityList).build();
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            
            return Response.serverError()
                    .entity(ex.getMessage())
                    .build();
        }
    }

    private URI getURI(UriInfo uriInfo, String id) {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(id);

        return uriBuilder.build();
    }

}
