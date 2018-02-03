package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Non CDI life cycle managed bean example. Java EE 7 has a CDI ViewScoped annotation.
 * 
 * Ejemplo de ciclo de vida estándar JSF, no CDI. Java EE 7 tiene este ciclo de vida como CDI.
 * 
 * @author Paco Saucedo
 */
@ManagedBean
@ViewScoped
public class ShipTypes extends AbstractController<ShipType> implements Serializable {

    private static final long serialVersionUID = 1935122046950251201L;
    
    @Inject
    private Logger logger;
    
    @Inject
    private ShipTypeFacade facade;
    
    @Inject
    private ResourceBundle resourceBundle;

    @Override
    protected void refreshData() {
        setElements(getFacade().findAll());
        setFilteredElements(null);
    }

    @Override
    protected ShipType getNewEntity() {
        return new ShipType();
    }

    @Override
    protected CrudFacade getFacade() {
        return facade;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
    
    @Override
    protected ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
    
}
