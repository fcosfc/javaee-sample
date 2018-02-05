package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.io.Serializable;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Paco Saucedo
 */
@Dependent
public class ShipTypesForm extends CrudForm<ShipType> implements Serializable {
    
    private static final long serialVersionUID = 415782204695023333L;        
    
}
