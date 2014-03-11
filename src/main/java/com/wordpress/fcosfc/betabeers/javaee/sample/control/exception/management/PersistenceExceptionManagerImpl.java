package com.wordpress.fcosfc.betabeers.javaee.sample.control.exception.management;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.persistence.PersistenceException;

/**
 *
 * @author Paco Saucedo
 */
public class PersistenceExceptionManagerImpl implements PersistenceExceptionManager, Serializable {

    @Override
    public void manageException(PersistenceException ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messagePersistenceError"),
                ex.getLocalizedMessage() == null ? ex.getMessage() : ex.getLocalizedMessage());                
    }
    
}
