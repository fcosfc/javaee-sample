package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.AbstractEntity;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CrudService;
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
 * @param <K>
 */
public abstract class CrudRestResource<T extends AbstractEntity, K extends AbstractDTO> {

    public abstract CrudService<T,K> getCrudService();
    
    public abstract Logger getLogger();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(K dto, @Context UriInfo uriInfo) {
        try {
            dto = getCrudService().create(dto);

            return Response.created(
                    getURI(uriInfo,
                            dto.getId()))
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
    public Response edit(@PathParam("id") String id, K dto) {
        try {
            dto.setId(id);
            getCrudService().update(dto);

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
            getCrudService().remove(id);

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
            K dto = getCrudService().find(id);

            return Response.ok(dto).build();
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
            List<K> dtoList = getCrudService().findAll();

            return Response.ok(dtoList).build();
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
