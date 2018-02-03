package com.wordpress.fcosfc.betabeers.javaee.sample.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Helper JSF presentation class.
 * 
 * Clase de ayuda para la presentación JSF.
 * 
 * @author NetBeans Team
 */

public class JsfUtil {

    private JsfUtil() {        
    }

    public static void addErrorMessage(String summary, String detail) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        RequestContext.getCurrentInstance().addCallbackParam("errorDetected", true);
    }
    
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }
}