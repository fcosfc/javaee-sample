package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.CountriesForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CountriesService;
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
@Named("countries")
@ViewScoped
public class CountriesController extends CrudController<CountryDTO> implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;
    
    @Inject
    public CountriesController(CountriesForm form, 
            CountriesService service, 
            @SampleResourceBundle ResourceBundle resourceBundle, 
            ExceptionManager exceptionManager) {
        super(form, service, resourceBundle, exceptionManager);
    }
    
    @Override
    protected CountryDTO getNewEntity() {
        return new CountryDTO();
    }
    
}
