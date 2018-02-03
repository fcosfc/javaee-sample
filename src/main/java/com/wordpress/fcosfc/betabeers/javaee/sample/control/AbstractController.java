package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 * Abstract class for CRUD presentation support.
 *
 * Clase abstracta para el soporte en la presentación de formularios CRUD.
 *
 * @author Paco Saucedo
 * @param <T>
 */
public abstract class AbstractController<T> {
    
    private List<T> elements;
    private List<T> filteredElements;
    private T currentEntity;
    private boolean creating;
    private boolean editing;

    protected abstract void refreshData();

    protected abstract T getNewEntity();

    protected abstract CrudFacade getFacade();

    protected abstract Logger getLogger();
    
    protected abstract ResourceBundle getResourceBundle();

    @PostConstruct
    protected void init() {
        try {
            refreshData();
        } catch (Exception ex) {
            manageException(ex);
        }
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getFilteredElements() {
        return filteredElements;
    }

    public void setFilteredElements(List<T> filteredElements) {
        this.filteredElements = filteredElements;
    }

    public T getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }

    public boolean isCreating() {
        return creating;
    }

    public void setCreating(boolean creating) {
        this.creating = creating;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String prepareCreate() {
        setCurrentEntity(getNewEntity());
        setCreating(true);
        setEditing(false);

        return null;
    }

    public String create() {
        try {
            getFacade().create(getCurrentEntity());
            setCurrentEntity(null);
            refreshData();

            JsfUtil.addSuccessMessage(
                    getResourceBundle()
                    .getString("messageRecordCreated"));
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }

    public String prepareEdit() {
        setEditing(true);
        setCreating(false);

        return null;
    }

    public String update() {
        try {
            getFacade().update(getCurrentEntity());
            setCurrentEntity(null);
            setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(
                    getResourceBundle()
                    .getString("messageRecordUpdated"));
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }

    public String remove() {
        try {
            getFacade().remove(getCurrentEntity());
            setCreating(false);
            setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(
                    getResourceBundle()
                    .getString("messageRecordRemoved"));
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }

    protected void manageException(Exception ex) {
        Throwable cause = ex.getCause();
        boolean exceptionManaged = false;

        while (cause != null && !exceptionManaged) {
            
            if (cause instanceof javax.persistence.PersistenceException) {
                JsfUtil.addErrorMessage(
                        getResourceBundle()
                                .getString("messagePersistenceError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());
                
                exceptionManaged = true;
            } else if (cause instanceof javax.persistence.OptimisticLockException) {
                JsfUtil.addErrorMessage(getResourceBundle()
                        .getString("messagePersistenceLockError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());
                
                exceptionManaged = true;
            } else {
                cause = cause.getCause();
            }

        }

        if (!exceptionManaged) {
            JsfUtil.addErrorMessage(
                    getResourceBundle()
                            .getString("messageErrorDetected"),
                    ex.getLocalizedMessage() == null ? ex.getMessage()
                            : ex.getLocalizedMessage());
        }

        getLogger().log(Level.SEVERE, null, ex);
    }
}
