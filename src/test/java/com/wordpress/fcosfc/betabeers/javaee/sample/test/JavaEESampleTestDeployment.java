package com.wordpress.fcosfc.betabeers.javaee.sample.test;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Arquillian base deployment
 * 
 * @author Paco Saucedo
 */
public class JavaEESampleTestDeployment {
    
    public static WebArchive deployment() {
        return ShrinkWrap
                .create(WebArchive.class, "javaee-sample-test.war")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("javaee-sample-test-ds.xml");
    }
}
