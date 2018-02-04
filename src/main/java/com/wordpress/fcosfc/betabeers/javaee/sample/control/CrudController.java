package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.CrudForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
import java.util.ResourceBundle;
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
public abstract class CrudController<T> {
    
    public abstract CrudForm getForm();
    
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

    protected void refreshData() {
        getForm().setElements(getFacade().findAll());
        getForm().setFilteredElements(null);
    }
    
    public String prepareCreate() {
        getForm().setCurrentEntity(getNewEntity());
        getForm().setCreating(true);
        getForm().setEditing(false);

        return null;
    }

    public String create() {
        try {
            getFacade().create(getForm().getCurrentEntity());
            getForm().setCurrentEntity(null);
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
        getForm().setEditing(true);
        getForm().setCreating(false);

        return null;
    }

    public String update() {
        try {
            getFacade().update(getForm().getCurrentEntity());
            getForm().setCurrentEntity(null);
            getForm().setEditing(false);
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
            getFacade().remove(getForm().getCurrentEntity());
            getForm().setCreating(false);
            getForm().setEditing(false);
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
