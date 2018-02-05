package com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi;

import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

/**
 * Example of how to produce injectable elements for CDI environments
 * 
 * Ejemplo de producción de elementos inyectables para entornos CDI
 * 
 * @author Paco Saucedo
 */
@Dependent
public class ResourceBundleProducer {
    
    private static final String MESSAGES_BUNDLE
            = "/com/wordpress/fcosfc/betabeers/javaee/sample/resource/Labels";
    
    @Produces
    @SampleResourceBundle
    public ResourceBundle produce() {
        return ResourceBundle.getBundle(MESSAGES_BUNDLE);
    }
}
