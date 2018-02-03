package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic JPA entity example, with JAXB and cache annotations 
 * 
 * Ejemplo básico de entidad JPA, con anotaciones para la serialización JAXB y caché.
 * 
 * @author Paco Saucedo
 */
@Entity
@XmlRootElement
@Cacheable
public class ShipType implements Serializable {
    
    @Id
    private String shipTypeCode;
    
    private String description;
        
    @Version
    private Long version;

    public ShipType() {
    }

    public ShipType(String shipTypeCode, String description) {
        this.shipTypeCode = shipTypeCode;
        this.description = description;
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(String shipTypeCode) {
        this.shipTypeCode = shipTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return 237 + (this.shipTypeCode != null ? this.shipTypeCode.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final ShipType other = (ShipType) obj;
            
            return this.shipTypeCode == null ? other.shipTypeCode == null 
                    : this.shipTypeCode.equals(other.shipTypeCode);
        }
    }
    
    @Override
    public String toString() {
        return "ShipType{" + "shipTypeCode=" + shipTypeCode + ", description=" + description + '}';
    }
    
}
