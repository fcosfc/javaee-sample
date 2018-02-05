package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.CountriesForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.SampleResourceBundle;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;
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
@Named
@ViewScoped
public class Countries extends CrudController<Country> implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;
    
    @Inject
    private CountriesForm form;
    
    @Inject
    private Logger logger;
    
    @Inject
    @SampleResourceBundle
    transient private ResourceBundle resourceBundle;
    
    @Inject
    private CountryFacade facade;
    
    @Inject
    private ExceptionManager exceptionManager;    

    @Override
    public CountriesForm getForm() {
        return form;
    }
    
    @Override
    protected Country getNewEntity() {
        return new Country();
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
    
    @Override
    protected ExceptionManager getExceptionManager() {
        return exceptionManager; 
    }
}
