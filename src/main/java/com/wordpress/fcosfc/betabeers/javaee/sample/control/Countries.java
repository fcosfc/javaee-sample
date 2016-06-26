package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Basic CDI example. A class with a dependency injected and a life cycle. Supports the presentation.
 * 
 * Ejemplo básico de CDI. Una clase con una dependencia y un ciclo de vida determinado. Da soporte a la presenteación.
 * 
 * @author Paco Saucedo
 */
@Named
@SessionScoped
public class Countries extends AbstractController<Country> implements Serializable {

    @Inject
    private Logger logger;
    
    @Inject
    private CountryFacade facade;
    
    public Countries() {
        super(Country.class);
    }

    @Override
    protected Country getNewEntity() {
        return new Country();
    }

    @Override
    protected CRUDFacade getFacade() {
        return facade;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
