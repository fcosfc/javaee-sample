package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.PaginationHelper;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author Paco Saucedo
 * @param <T>
 */
public abstract class AbstractController<T> {

    private final Class<T> entityClass;
    private PaginationHelper<T> paginationHelper;
    private String filterCriteria;
    private T currentEntity;
    private boolean creating, editing;
    private int currentPageIndex;

    public AbstractController() {
        entityClass = null;
    }

    public AbstractController(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract T getNewEntity();

    protected abstract CRUDFacade getFacade();

    protected abstract Logger getLogger();

    @PostConstruct
    protected void init() {
        try {
            paginationHelper = null;
            filterCriteria = "%";
            refreshData();
        } catch (Exception ex) {
            manageException(ex);
            getLogger().log(Level.SEVERE, null, ex);
        }
    }

    public PaginationHelper<T> getPaginationHelper() {
        return paginationHelper;
    }

    public T getCurrentEntity() {
        return currentEntity;
    }

    public String getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(String filterCriteria) {
        this.filterCriteria = filterCriteria;
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

    public String filter() {
        setCreating(false);
        setEditing(false);
        refreshData();

        return null;
    }

    public String prepareCreate() {
        currentEntity = getNewEntity();
        setCreating(true);
        setEditing(false);

        return null;
    }

    public String create() {
        try {
            getFacade().create(currentEntity);
            currentEntity = getNewEntity();
            refreshData();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordCreated"));
        } catch (Exception ex) {
            manageException(ex);
            getLogger().log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String prepareEdit() {
        currentEntity = paginationHelper.getCurrentPage().getRowData();
        setEditing(true);
        setCreating(false);

        return null;
    }

    public String update() {
        try {
            getFacade().update(currentEntity);
            setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordUpdated"));
        } catch (Exception ex) {
            manageException(ex);
            getLogger().log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String remove() {
        try {
            currentEntity = paginationHelper.getCurrentPage().getRowData();
            getFacade().remove(currentEntity);
            setCreating(false);
            setEditing(false);
            refreshData();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordRemoved"));
        } catch (Exception ex) {
            manageException(ex);
            getLogger().log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String cancel() {
        setCreating(false);
        setEditing(false);

        return null;
    }

    protected void refreshData() {
        if (paginationHelper == null) {
            currentPageIndex = 1;
        } else {
            currentPageIndex = paginationHelper.getCurrentPageIndex();
        }
        paginationHelper = new PaginationHelper<T>(entityClass, getFacade().findByFilter(filterCriteria), currentPageIndex);
    }

    protected void manageException(Exception ex) {
        Throwable cause;

        cause = ex.getCause();
        while (cause != null) {
            if (cause.getClass().getName().equals("javax.persistence.PersistenceException")) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messagePersistenceError"),
                    ex.getLocalizedMessage() == null ? ex.getMessage() : ex.getLocalizedMessage());
                break;
            } else {
                cause = cause.getCause();
            }
        }

        if (cause == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"),
                    ex.getLocalizedMessage() == null ? ex.getMessage() : ex.getLocalizedMessage());
        }
    }
}
