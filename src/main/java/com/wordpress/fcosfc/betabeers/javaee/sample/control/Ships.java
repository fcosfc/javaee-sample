package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
public class Ships extends AbstractController<Ship> implements Serializable{

    private static final Logger logger = Logger.getLogger(Ships.class.getName());
    
    @Inject
    protected ShipFacade facade;
    
    public Ships() {
        super(Ship.class);
    }

    @Override
    protected Ship getNewEntity() {
        return new Ship();
    }

    @Override
    protected CRUDFacade getFacade() {
        return facade;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
    
    public List<Country> getAllCountries() {
        try {
            return facade.getAllCountries();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            getLogger().log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
    
    public List<ShipType> getAllShipTypes() {
        try {
            return facade.getAllShipTypes();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            getLogger().log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
}