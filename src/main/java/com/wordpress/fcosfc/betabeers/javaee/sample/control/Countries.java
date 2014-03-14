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
 *
 * @author Paco Saucedo
 */
@Named
@SessionScoped
public class Countries extends AbstractController<Country> implements Serializable{

    private static final Logger logger = Logger.getLogger(ShipTypes.class.getName());
    
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
