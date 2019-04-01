package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.ShipsForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CountriesService;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.ShipTypesService;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.ShipsService;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.SampleResourceBundle;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller example.
 *
 * Ejemplo de controlador de la interacción entre el backend y el frontend.
 *
 * @author Paco Saucedo
 */
@Named("ships")
@ViewScoped
public class ShipsController extends CrudController<ShipDTO> implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Inject
    private CountriesService countriesService;

    @Inject
    private ShipTypesService shipTypesService;

    private List<CountryDTO> allCountries;
    private List<ShipTypeDTO> allShipTypes;

    @Inject
    public ShipsController(ShipsForm form,
            ShipsService service,
            @SampleResourceBundle ResourceBundle resourceBundle,
            ExceptionManager exceptionManager) {
        super(form, service, resourceBundle, exceptionManager);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        try {
            allCountries = countriesService.findAll();
            allShipTypes = shipTypesService.findAll();
        } catch (Exception ex) {
            getExceptionManager().manageException(ex);
        }
    }

    public List<CountryDTO> getAllCountries() {
        return allCountries;
    }

    public List<ShipTypeDTO> getAllShipTypes() {
        return allShipTypes;
    }

    @Override
    protected ShipDTO getNewEntity() {
        return new ShipDTO();
    }

    @FacesConverter(forClass = ShipTypeDTO.class)
    public static class ShipTypeConverter implements Converter {

        @Inject
        private Logger logger;

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }

            try {
                return getShipsController(context).getShipType(value);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

                throw new ConverterException(new FacesMessage(value + " not valid"), ex);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof ShipTypeDTO) {
                return ((ShipTypeDTO) value).getShipTypeCode();
            } else {
                return null;
            }
        }
    }

    @FacesConverter(forClass = CountryDTO.class)
    public static class CountryConverter implements Converter {

        @Inject
        private Logger logger;

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }

            try {
                return getShipsController(context).getCountry(value);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

                throw new ConverterException(new FacesMessage(value + " not valid"), ex);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof CountryDTO) {
                return ((CountryDTO) value).getIsoCode();
            } else {
                return null;
            }
        }
    }

    protected static ShipsController getShipsController(FacesContext context) {
        return (ShipsController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "ships");
    }

    protected ShipTypeDTO getShipType(String shipTypeCode) {
        try {
            return shipTypesService.find(shipTypeCode);
        } catch (Exception ex) {
            getExceptionManager().manageException(ex);
        }

        return null;
    }

    protected CountryDTO getCountry(String isoCode) {
        try {
            return countriesService.find(isoCode);
        } catch (Exception ex) {
            getExceptionManager().manageException(ex);
        }

        return null;
    }
}
