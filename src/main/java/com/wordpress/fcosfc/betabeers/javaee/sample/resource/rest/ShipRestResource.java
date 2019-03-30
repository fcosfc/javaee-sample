package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Ship REST resource.
 *
 * Recurso REST para los buques.
 *
 * @author Paco
 */
@ApplicationScoped
@Path("ship")
public class ShipRestResource extends CrudRestResource<Ship> {
    
    @Inject 
    private Logger logger;

    @Inject
    protected ShipFacade shipFacade;

    @Override
    public CrudFacade<Ship> getCrudFacade() {
        return shipFacade;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}
