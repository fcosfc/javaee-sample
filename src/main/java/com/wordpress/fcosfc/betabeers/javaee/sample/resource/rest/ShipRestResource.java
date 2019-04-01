package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CrudService;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.ShipsService;
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
public class ShipRestResource extends CrudRestResource<Ship, ShipDTO> {
    
    @Inject 
    private Logger logger;

    @Inject
    protected ShipsService shipsService;

    @Override
    public CrudService<Ship, ShipDTO> getCrudService() {
        return shipsService;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }    

}
