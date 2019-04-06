package com.wordpress.fcosfc.betabeers.javaee.sample.test;

import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Arquillian base deployment for REST tests
 * 
 * @author Paco Saucedo
 */
public class JavaEESampleRestTestDeployment {
    
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi.LoggerProducer.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.util.EntityFactory.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.util.DtoFactory.class)
                .addPackage("com.wordpress.fcosfc.betabeers.javaee.sample.entity")
                .addPackage("com.wordpress.fcosfc.betabeers.javaee.sample.dto")
                .addPackage("com.wordpress.fcosfc.betabeers.javaee.sample.service")
                .addPackage("com.wordpress.fcosfc.betabeers.javaee.sample.repository")
                .addPackage("com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest");
    }
   
}
