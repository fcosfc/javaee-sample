package com.wordpress.fcosfc.betabeers.javaee.sample.control.exception.management;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import java.io.Serializable;
import javax.persistence.PersistenceException;

/**
 *
 * @author Paco Saucedo
 */
@DerbyPersistenceExceptionManager
public class DerbyPersistenceExceptionManagerImpl implements PersistenceExceptionManager, Serializable {

    @Override
    public void manageException(PersistenceException ex) {
        JsfUtil.addErrorMessage("Test", ex.getLocalizedMessage() == null ? ex.getMessage() : ex.getLocalizedMessage());
    }

}
