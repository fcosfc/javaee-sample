package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.ShipTypesForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
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
public class ShipTypes extends CrudController<ShipType> implements Serializable {

    private static final long serialVersionUID = 1935122046950251201L;

    @Inject
    public ShipTypes(ShipTypesForm form, 
            ShipTypeFacade facade, 
            @SampleResourceBundle ResourceBundle resourceBundle, 
            ExceptionManager exceptionManager) {
        super(form, facade, resourceBundle, exceptionManager);
    }         
       
    @Override
    protected ShipType getNewEntity() {
        return new ShipType();
    }
    
}
