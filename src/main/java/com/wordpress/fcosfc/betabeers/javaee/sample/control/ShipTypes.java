package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
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
public class ShipTypes extends AbstractController<ShipType> implements Serializable{

    private static final Logger logger = Logger.getLogger(ShipTypes.class.getName());
    
    @Inject
    protected ShipTypeFacade facade;
    
    public ShipTypes() {
        super(ShipType.class);
    }

    @Override
    protected ShipType getNewEntity() {
        return new ShipType();
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
