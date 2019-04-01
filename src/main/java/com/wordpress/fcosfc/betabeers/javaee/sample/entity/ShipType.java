package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Basic JPA entity example, with cache annotations 
 * 
 * Ejemplo básico de entidad JPA y caché.
 * 
 * @author Paco Saucedo
 */
@Entity
@Cacheable
public class ShipType extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -9192775205591008104L;
    
    @Id
    private String shipTypeCode;
    
    private String description;

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
