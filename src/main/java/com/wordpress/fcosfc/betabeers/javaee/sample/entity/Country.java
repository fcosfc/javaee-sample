package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic JPA entity example, with JAXB and cache annotations
 *
 * Ejemplo básico de entidad JPA, con anotaciones para la serialización JAXB y
 * caché.
 *
 * @author Paco Saucedo
 */
@Entity
@Table(name = "COUNTRIES")
@XmlRootElement
@Cacheable
public class Country implements Serializable {

    @Id
    @Column(name = "ISO_CODE", length = 2)
    private String isoCode;

    @Column(nullable = false, length = 100)
    private String name;

    @Version
    private Long version;

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

    public Long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return 485 + (this.isoCode != null ? this.isoCode.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final Country other = (Country) obj;
            
            return this.isoCode == null ? other.isoCode == null 
                    : this.isoCode.equals(other.isoCode);
        }
    }

    @Override
    public String toString() {
        return "Country{" + "isoCode=" + isoCode + ", name=" + name + '}';
    }

}
