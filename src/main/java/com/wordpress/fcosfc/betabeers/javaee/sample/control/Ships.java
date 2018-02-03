package com.wordpress.fcosfc.betabeers.javaee.sample.control;

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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

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
public class Ships extends AbstractController<Ship> implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    
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

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        try {
            allCountries = countryFacade.findAll();
            allShipTypes = shipTypeFacade.findAll();
        } catch (Exception ex) {
            manageException(ex);
        }
    }

    @Override
    protected void refreshData() {
        setElements(getFacade().findAll());
        setFilteredElements(null);
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

    protected ShipType getShipType(String shipTypeCode) {
        try {
            return shipTypeFacade.find(shipTypeCode);
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }

    protected Country getCountry(String isoCode) {
        try {
            return countryFacade.find(isoCode);
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }
}
