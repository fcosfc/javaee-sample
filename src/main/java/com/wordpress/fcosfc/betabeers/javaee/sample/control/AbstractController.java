package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
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
    
    protected abstract ExceptionManager getExceptionManager();

    @PostConstruct
    protected void init() {
        try {
            refreshData();
        } catch (Exception ex) {
            getExceptionManager().manageException(ex);
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
            getExceptionManager().manageException(ex);
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
            getExceptionManager().manageException(ex);
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
            getExceptionManager().manageException(ex);
        }

        return null;
    }
    
}
