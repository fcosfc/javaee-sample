package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CrudService;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.ShipTypesService;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * ShipType REST resource. 
 * 
 * Recurso REST para los tipos de buque.
 * 
 * @author Paco
 */
@ApplicationScoped
@Path("shiptype")
public class ShipTypeRestResource extends CrudRestResource<ShipType, ShipTypeDTO> {
    
    @Inject 
    private Logger logger;
    
    @Inject
    protected ShipTypesService shipTypesService;

    @Override
    public CrudService<ShipType, ShipTypeDTO> getCrudService() {
        return shipTypesService;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }    
        
}
