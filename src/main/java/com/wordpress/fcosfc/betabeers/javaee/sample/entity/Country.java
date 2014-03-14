package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Basic JPA entity example with table and columns definitions, plus a relation with another entity.
 * 
 * Ejemplo básico de entidad JPA con definiciones de columnas y tabla, además de una relación con otra entidad.
 * 
 * @author Paco Saucedo
 */
@Entity
@Table(name="COUNTRIES")
@XmlRootElement
public class Country implements Serializable {
    
    @Id
    @Column(name = "ISO_CODE", length = 2)
    @Size(min = 2, max = 2)
    private String isoCode;
    
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "flag", fetch = FetchType.LAZY)
    private List<Ship> shipList;
    
    public Country() {
    }

    public Country(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @XmlTransient
    public List<Ship> getShipList() {
        return shipList;
    }

    public void setShipList(List<Ship> shipList) {
        this.shipList = shipList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.isoCode != null ? this.isoCode.hashCode() : 0);
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
        final Country other = (Country) obj;
        if ((this.isoCode == null) ? (other.isoCode != null) : !this.isoCode.equals(other.isoCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "isoCode=" + isoCode + ", name=" + name + '}';
    }
    
}
