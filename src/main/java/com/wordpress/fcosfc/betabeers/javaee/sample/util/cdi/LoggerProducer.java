package com.wordpress.fcosfc.betabeers.javaee.sample.util.cdi;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Example of how to produce injectable elements for CDI environments
 * 
 * Ejemplo de producción de elementos inyectables para entornos CDI
 * 
 * @author Paco Saucedo
 */
@Dependent
public class LoggerProducer {

    @Produces
    public Logger produce(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
