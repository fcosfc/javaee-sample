package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.stereotype.CRUDController;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * CDI Example, with the use of a custom stereotype.
 * 
 * Ejemplo CDI, con el uso de un estereotipo.
 * 
 * @author Paco Saucedo
 */
@CRUDController
public class Ships extends AbstractController<Ship> implements Serializable{

    private static final Logger logger = Logger.getLogger(Ships.class.getName());
    
    @Inject
    private ShipFacade shipFacade;
    
    @Inject
    private CountryFacade countryFacade;
    
    @Inject
    private ShipTypeFacade shipTypeFacade;
    
    private List<Country> allCountries;
    private List<ShipType> allShipTypes;
    private String flag, shipType;
    
    public Ships() {
        super(Ship.class);
    }

    @Override
    protected Ship getNewEntity() {
        return new Ship();
    }

    @Override
    protected CRUDFacade getFacade() {
        return shipFacade;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
    
    public List<Country> getAllCountries() {
        return allCountries;
    }
    
    public List<ShipType> getAllShipTypes() {
        return allShipTypes;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    
    @Override
    public String create() {
        try {
            getCurrentEntity().setFlag(countryFacade.find(flag));
            getCurrentEntity().setShipType(shipTypeFacade.find(shipType));
            
            super.create();
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }
    
    @Override
    public String update() {
        try {
            getCurrentEntity().setFlag(countryFacade.find(flag));
            getCurrentEntity().setShipType(shipTypeFacade.find(shipType));
            
            super.update();
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }
    
    @Override
    protected void refreshData() {
        super.refreshData();
        allCountries = countryFacade.findAll();
        allShipTypes = shipTypeFacade.findAll();
    }
    
}