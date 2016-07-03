package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.stereotype.CRUDController;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import javax.inject.Inject;

/**
 * CDI Example, with the use of a custom stereotype.
 * 
 * Ejemplo CDI, con el uso de un estereotipo.
 * 
 * @author Paco Saucedo
 */
@CRUDController
public class Ships extends AbstractController<Ship> implements Serializable {

    @Inject
    private Logger logger;
    
    @Inject
    private ShipFacade shipFacade;
    
    @Inject
    private CountryFacade countryFacade;
    
    @Inject
    private ShipTypeFacade shipTypeFacade;
    
    private List<Country> allCountries;
    private List<ShipType> allShipTypes;
    
    public Ships() {
        super(Ship.class);
    }

    @Override
    protected Ship getNewEntity() {
        return new Ship();
    }

    @Override
    protected CrudFacade getFacade() {
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
    
    @FacesConverter(forClass = ShipType.class)
    public static class ShipTypeConverter implements Converter {

        @Inject
        private Logger logger;
        
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }

            try {
                Ships ships = (Ships) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "ships");

                return ships.getShipType(value);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                throw new ConverterException(new FacesMessage(value + "not valid"), ex);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof ShipType) {
                return ((ShipType) value).getShipTypeCode();
            } else {
                return null;
            }
        }
    }
    
    @FacesConverter(forClass = Country.class)
    public static class CountryConverter implements Converter {

        @Inject
        private Logger logger;
        
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }

            try {
                Ships ships = (Ships) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "ships");

                return ships.getCountry(value);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                throw new ConverterException(new FacesMessage(value + "not valid"), ex);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof Country) {
                return ((Country) value).getIsoCode();
            } else {
                return null;
            }
        }
    }
    
    @Override
    protected void refreshData() {
        super.refreshData();
        allCountries = countryFacade.findAll();
        allShipTypes = shipTypeFacade.findAll();
    }

    protected ShipType getShipType(String shipTypeCode) {
        try {
            return shipTypeFacade.find(shipTypeCode);                    
        }  catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }
    
    protected Country getCountry(String isoCode) {
        try {
            return countryFacade.find(isoCode);                    
        }  catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }
}