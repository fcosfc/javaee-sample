package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic entity example
 * 
 * @author Paco Saucedo
 */
@Entity
@XmlRootElement
public class ShipType implements Serializable {
    
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
        int hash = 5;
        hash = 89 * hash + (this.shipTypeCode != null ? this.shipTypeCode.hashCode() : 0);
        hash = 89 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShipType other = (ShipType) obj;
        if ((this.shipTypeCode == null) ? (other.shipTypeCode != null) : !this.shipTypeCode.equals(other.shipTypeCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShipType{" + "shipTypeCode=" + shipTypeCode + ", description=" + description + '}';
    }
    
}
