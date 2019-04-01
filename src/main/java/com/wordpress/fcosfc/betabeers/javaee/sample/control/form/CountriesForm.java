package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import java.io.Serializable;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Paco Saucedo
 */
@Dependent
public class CountriesForm extends CrudForm<CountryDTO> implements Serializable {
    
    private static final long serialVersionUID = 425322236195023312L;        
    
}
