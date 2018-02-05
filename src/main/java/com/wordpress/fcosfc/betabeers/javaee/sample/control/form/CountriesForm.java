package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import java.io.Serializable;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Paco Saucedo
 */
@Dependent
public class CountriesForm extends CrudForm<Country> implements Serializable {
    
    private static final long serialVersionUID = 425322236195023312L;        
    
}
