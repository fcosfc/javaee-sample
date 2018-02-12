package com.wordpress.fcosfc.betabeers.javaee.sample.util;

import com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.SampleResourceBundle;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
@Dependent
public class ExceptionManager implements Serializable {

    private static final long serialVersionUID = 4444172041950253457L;

    @Inject
    private Logger logger;

    @Inject
    @SampleResourceBundle
    private transient ResourceBundle resourceBundle;

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
