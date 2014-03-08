package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Full entity example, with table/columns definitions and bean validations, 
 * 
 * @author Paco Saucedo
 */
@Entity
@Table(name = "SHIPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ship.FindAll", query = "SELECT s FROM Ship s ORDER BY s.name"),
    @NamedQuery(name = "Ship.FindByNameFilter", query = "SELECT s FROM Ship s WHERE upper(s.name) like upper(:nameFilter) ORDER BY s.name")})
public class Ship implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "SHIP_ID")
    private Long shipId;
    
    @Column(name = "IMO_CODE", nullable = false, unique = true)
    @NotNull
    private Integer imoCode;
    
    @Column(name = "NAME", nullable = false, length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @Column(name = "GROSS_TONS", nullable = false)
    @NotNull
    @Min(1)
    @Max(50000)
    private Integer grossTons;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_BUILT")
    @Past
    private Date dateBuilt;
    
    @ManyToOne
    private Country flag;
    
    @ManyToOne
    private ShipType shipType;

    public Ship() {
    }

    public Ship(Integer imoCode, String name, Integer grossTons, Date dateBuilt, Country flag, ShipType shipType) {
        this.imoCode = imoCode;
        this.name = name;
        this.grossTons = grossTons;
        this.dateBuilt = dateBuilt;
        this.flag = flag;
        this.shipType = shipType;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Integer getImoCode() {
        return imoCode;
    }

    public void setImoCode(Integer imoCode) {
        this.imoCode = imoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrossTons() {
        return grossTons;
    }

    public void setGrossTons(Integer grossTons) {
        this.grossTons = grossTons;
    }

    public Date getDateBuilt() {
        return dateBuilt;
    }

    public void setDateBuilt(Date dateBuilt) {
        this.dateBuilt = dateBuilt;
    }

    public Country getFlag() {
        return flag;
    }

    public void setFlag(Country flag) {
        this.flag = flag;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.imoCode != null ? this.imoCode.hashCode() : 0);
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
        final Ship other = (Ship) obj;
        if (this.imoCode != other.imoCode && (this.imoCode == null || !this.imoCode.equals(other.imoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ship{" + "imoCode=" + imoCode + ", name=" + name + '}';
    }
    
    
}
