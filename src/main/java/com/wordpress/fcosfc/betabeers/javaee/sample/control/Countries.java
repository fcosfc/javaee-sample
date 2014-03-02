package com.wordpress.fcosfc.betabeers.javaee.sample.control;

import com.wordpress.fcosfc.betabeers.javaee.sample.control.util.JsfUtil;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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
    private DataModel<Country> countries;
    private Country currentCountry;
    private boolean creating, editing;

    public Countries() {
        creating = false;
        editing = false;
    }

    @PostConstruct
    protected void init() {
        try {
            refreshCountries();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/com/wordpress/fcosfc/betabeers/javaee/sample/resource/label").getString("messageErrorDetected"));
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public DataModel<Country> getCountries() {
        return countries;
    }

    public void setCountries(DataModel<Country> countries) {
        this.countries = countries;
    }

    public Country getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(Country currentCountry) {
        this.currentCountry = currentCountry;
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
        currentCountry = countries.getRowData();
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
            currentCountry = countries.getRowData();
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
        countries = new ListDataModel<Country>(countryFacade.findAll());
    }
}
