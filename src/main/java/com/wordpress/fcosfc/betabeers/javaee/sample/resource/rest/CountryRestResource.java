package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CountriesService;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CrudService;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * Country REST resource.
 * 
 * Recurso REST para la entidad pais.
 * 
 * @author Paco Saucedo
 */
@ApplicationScoped
@Path("country")
public class CountryRestResource extends CrudRestResource<Country, CountryDTO> {
    
    @Inject 
    private Logger logger;
    
    @Inject
    protected CountriesService countriesService;

    @Override
    public CrudService<Country, CountryDTO> getCrudService() {
        return countriesService;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
       
}
