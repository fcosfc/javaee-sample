package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import java.io.Serializable;
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
public abstract class AbstractController<T> implements Serializable {

    private List<T> elements, filteredElements;
    private T currentEntity;
    private boolean creating, editing;

    protected abstract void refreshData();
    
    protected abstract T getNewEntity();

    protected abstract CrudFacade getFacade();

    protected abstract Logger getLogger();

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

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messageRecordCreated"));
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

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messageRecordUpdated"));
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

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messageRecordRemoved"));
        } catch (Exception ex) {
            manageException(ex);
        }

        return null;
    }

    protected void manageException(Exception ex) {
        Throwable cause;

        cause = ex.getCause();
        while (cause != null) {
            if (cause.getClass().getName().equals("javax.persistence.PersistenceException")) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messagePersistenceError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());
                break;
            } else if (cause.getClass().getName().equals("javax.persistence.OptimisticLockException")) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messagePersistenceLockError"),
                        cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());
                break;
            } else {
                cause = cause.getCause();
            }
        }

        if (cause == null) {
            cause = ex.getCause();

            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels").getString("messageErrorDetected"),
                    cause.getLocalizedMessage() == null ? cause.getMessage() : cause.getLocalizedMessage());
        }

        getLogger().log(Level.SEVERE, null, ex);
    }
}
