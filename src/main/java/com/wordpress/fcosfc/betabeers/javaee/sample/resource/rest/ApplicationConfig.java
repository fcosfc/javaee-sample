package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Application config class for REST resources.
 * 
 * Clase de configuración necesaria para los ejemplos de recursos REST.
 * 
 * @author Paco Saucedo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest.CountryRestResource.class);
        resources.add(com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest.ShipRestResource.class);
        resources.add(com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest.ShipTypeRestResource.class);
    }
    
}
