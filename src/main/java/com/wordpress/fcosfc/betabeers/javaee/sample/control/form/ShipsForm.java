package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import java.io.Serializable;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Paco Saucedo
 */
@Dependent
public class ShipsForm extends CrudForm<ShipDTO> implements Serializable {
    
    private static final long serialVersionUID = 123542204695023344L;        
    
}
