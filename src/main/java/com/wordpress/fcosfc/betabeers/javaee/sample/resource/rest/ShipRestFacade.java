package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Ship entity REST facade.
 *
 * Fachada del recurso REST para la entidad Ship.
 *
 * @author Paco
 */
@ApplicationScoped
@Path("ship")
public class ShipRestFacade extends CrudRestFacade<Ship> {

    @Inject
    protected ShipFacade shipFacade;

    @Override
    public CrudFacade<Ship> getCrudFacade() {
        return shipFacade;
    }

}
