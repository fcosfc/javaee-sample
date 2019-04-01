package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract entity
 * 
 * Entidad abstracta
 * 
 * @author Paco Saucedo
 */
@MappedSuperclass
public class AbstractEntity {
    
    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }    

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
