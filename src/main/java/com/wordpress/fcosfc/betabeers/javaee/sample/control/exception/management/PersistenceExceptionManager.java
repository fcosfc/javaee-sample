package com.wordpress.fcosfc.betabeers.javaee.sample.control.exception.management;

import javax.persistence.PersistenceException;

/**
 *
 * @author Paco Saucedo
 */
public interface PersistenceExceptionManager {
    
    void manageException(PersistenceException ex);
}
