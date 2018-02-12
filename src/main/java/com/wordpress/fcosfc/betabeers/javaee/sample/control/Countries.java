package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.CountriesForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.SampleResourceBundle;
import java.io.Serializable;
import java.util.ResourceBundle;
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
    public Countries(CountriesForm form, 
            CountryFacade facade, 
            @SampleResourceBundle ResourceBundle resourceBundle, 
            ExceptionManager exceptionManager) {
        super(form, facade, resourceBundle, exceptionManager);
    }
    
    @Override
    protected Country getNewEntity() {
        return new Country();
    }
    
}
