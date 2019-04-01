package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.form.CrudForm;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.service.CrudService;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.ExceptionManager;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;

/**
 * Abstract class for CRUD presentation support.
 *
 * Clase abstracta para el soporte en la presentación de formularios CRUD.
 *
 * @author Paco Saucedo
 * @param <K>
 */
public abstract class CrudController<K extends AbstractDTO> {
    
    private final CrudForm form;

    private final CrudService service;
    
    private final ResourceBundle resourceBundle;
    
    private final ExceptionManager exceptionManager;
    
    protected abstract K getNewEntity();    
    
    public CrudController(CrudForm form, 
            CrudService service, 
            ResourceBundle resourceBundle, 
            ExceptionManager exceptionManager) {
        this.form = form;
        this.service = service;
        this.resourceBundle = resourceBundle;
        this.exceptionManager = exceptionManager;
    }
    
    @PostConstruct
    protected void init() {
        try {
            refreshData();
        } catch (Exception ex) {
            exceptionManager.manageException(ex);
        }
    }

    protected void refreshData() {
        form.setElements(service.findAll());
        form.setFilteredElements(null);
    }
    
    public String prepareCreate() {
        form.setCurrentEntity(getNewEntity());
        form.setCreating(true);
        form.setEditing(false);

        return null;
    }

    public String create() {
        try {
            service.create(form.getCurrentEntity());
            form.setCurrentEntity(null);
            refreshData();

            JsfUtil.addSuccessMessage(
                    resourceBundle
                    .getString("messageRecordCreated"));
        } catch (Exception ex) {
            exceptionManager.manageException(ex);
        }

        return null;
    }

    public String prepareEdit() {
        form.setEditing(true);
        form.setCreating(false);

        return null;
    }

    public String update() {
        try {
            service.update(form.getCurrentEntity());
            form.setCurrentEntity(null);
            form.setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(
                    resourceBundle
                    .getString("messageRecordUpdated"));
        } catch (Exception ex) {
            exceptionManager.manageException(ex);
        }

        return null;
    }

    public String remove() {
        try {
            service.remove(form.getCurrentEntity().getId());
            form.setCreating(false);
            form.setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(
                    resourceBundle
                    .getString("messageRecordRemoved"));
        } catch (Exception ex) {
            exceptionManager.manageException(ex);
        }

        return null;
    }

    public CrudForm getForm() {
        return form;
    }

    protected CrudService getService() {
        return service;
    }

    protected ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    protected ExceptionManager getExceptionManager() {
        return exceptionManager;
    }    
     
}
