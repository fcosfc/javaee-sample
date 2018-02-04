package com.wordpress.fcosfc.betabeers.javaee.sample.util;

import com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.SampleResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
public class ExceptionManager {

    @Inject
    private Logger logger;

    @Inject
    @SampleResourceBundle
    private ResourceBundle resourceBundle;

    public void manageException(Exception ex) {
        Throwable cause = ex.getCause();
        boolean exceptionManaged = false;

        while (cause != null && !exceptionManaged) {

            if (cause instanceof javax.persistence.OptimisticLockException) {
                JsfUtil.addErrorMessage(
                        resourceBundle.getString("messagePersistenceLockError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());

                exceptionManaged = true;
            } else if (cause instanceof javax.persistence.PersistenceException) {
                JsfUtil.addErrorMessage(
                        resourceBundle.getString("messagePersistenceError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());

                exceptionManaged = true;
            } else {
                cause = cause.getCause();
            }

        }

        if (!exceptionManaged) {
            JsfUtil.addErrorMessage(
                    resourceBundle.getString("messageErrorDetected"),
                    ex.getLocalizedMessage() == null ? ex.getMessage()
                            : ex.getLocalizedMessage());
        }

        logger.log(Level.SEVERE, null, ex);
    }

}
