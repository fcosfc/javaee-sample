package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paco
 * @param <T>
 */
public interface RestEntity<T> {
    
    @XmlTransient
    String getId();
    
}
