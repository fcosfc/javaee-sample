package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Country entity REST facade.
 * 
 * Fachada del recurso REST para la entidad Country.
 * 
 * @author Paco Saucedo
 */
@ApplicationScoped
@Path("country")
public class CountryRestFacade extends CrudRestFacade<Country> {
    
    @Inject
    protected CountryFacade countryFacade;

    @Override
    public CrudFacade<Country> getCrudFacade() {
        return countryFacade;
    }
       
}
