package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.ShipTypesForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.ShipTypesService;
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
@Named("shipTypes")
@ViewScoped
public class ShipTypesController extends CrudController<ShipTypeDTO> implements Serializable {

    private static final long serialVersionUID = 1935122046950251201L;

    @Inject
    public ShipTypesController(ShipTypesForm form, 
            ShipTypesService service, 
            @SampleResourceBundle ResourceBundle resourceBundle, 
            ExceptionManager exceptionManager) {
        super(form, service, resourceBundle, exceptionManager);
    }         
       
    @Override
    protected ShipTypeDTO getNewEntity() {
        return new ShipTypeDTO();
    }
    
}
