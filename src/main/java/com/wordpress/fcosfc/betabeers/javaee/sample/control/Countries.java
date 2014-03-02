package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.PaginationHelper;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Paco Saucedo
 */
@Named
@SessionScoped
public class Countries implements Serializable {

    private static final Logger logger = Logger.getLogger(Countries.class.getName());

    @Inject
    private CountryFacade countryFacade;
    private PaginationHelper<Country> paginationHelper;
    private String nameFilter;
    private Country currentCountry;
    private boolean creating, editing;
    @Resource
    private int pageSize;
    private int currentPageIndex;

    public Countries() {
        creating = false;
        editing = false;
    }

    @PostConstruct
    protected void init() {
        try {
            paginationHelper = null;
            nameFilter = "%";
            refreshCountries();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public PaginationHelper<Country> getPaginationHelper() {
        return paginationHelper;
    }       

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public Country getCurrentCountry() {
        return currentCountry;
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
        refreshCountries();
        
        return null;
    }
    public String prepareCreate() {
        currentCountry = new Country();
        setCreating(true);
        setEditing(false);

        return null;
    }

    public String create() {
        try {
            countryFacade.create(currentCountry);
            setCreating(false);
            refreshCountries();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordCreated"));
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String prepareEdit() {
        currentCountry = paginationHelper.getCurrentPage().getRowData();
        setEditing(true);
        setCreating(false);

        return null;
    }

    public String update() {
        try {
            countryFacade.update(currentCountry);
            setEditing(false);
            refreshCountries();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordUpdated"));
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public String remove() {
        try {
            currentCountry = paginationHelper.getCurrentPage().getRowData();
            countryFacade.remove(currentCountry);
            setCreating(false);
            setEditing(false);
            refreshCountries();
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageRecordRemoved"));
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String cancel() {
        setCreating(false);
        setEditing(false);
            
        return null;
    }
    
    protected void refreshCountries() {
        if (paginationHelper == null) {
            currentPageIndex = 1;
        } else {
            currentPageIndex = paginationHelper.getCurrentPageIndex();
        }
        paginationHelper = new PaginationHelper<Country>(Country.class, countryFacade.findByFilter(nameFilter), currentPageIndex);
    }
}
