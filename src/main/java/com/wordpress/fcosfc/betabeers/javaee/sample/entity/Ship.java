package com.wordpress.fcosfc.betabeers.javaee.sample.entity;

import com.wordpress.fcosfc.betabeers.javaee.sample.validation.ImoCode;
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
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Full JPA entity example, with table/columns definitions, bean validations and named queries.
 * 
 * Ejemplo completo de entidad JPA, con definiciones de tabla/columnas, validaciones y consultas almacenadas.
 * 
 * @author Paco Saucedo
 */
@Entity
@Table(name = "SHIPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ship.FindAll", query = "SELECT s FROM Ship s ORDER BY s.name"),
    @NamedQuery(name = "Ship.FindByNameFilter", query = "SELECT s FROM Ship s WHERE upper(s.name) like upper(:nameFilter) ORDER BY s.name")})
public class Ship implements Serializable, RestEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "SHIP_ID")
    private Long shipId;
    
    @Column(name = "IMO_CODE", nullable = false, unique = true)
    @NotNull
    @ImoCode
    private Integer imoCode;
    
    @Column(name = "NAME", nullable = false, length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @Column(name = "GROSS_TONS", nullable = false)
    @NotNull
    @Min(1)
    @Max(200000)
    private Integer grossTons;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_BUILT")
    @Past
    private Date dateBuilt;
    
    @ManyToOne(optional = false)
    private Country flag;
    
    @ManyToOne(optional = false)
    private ShipType shipType;
    
    @Version
    private Long version;

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

    @Override
    public String getId() {
        return shipId.toString();
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

    public Long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return 161 + (this.imoCode != null ? this.imoCode.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final Ship other = (Ship) obj;
            
            return this.imoCode == null ? other.imoCode == null 
                    : this.imoCode.equals(other.imoCode);
        }
    }

    @Override
    public String toString() {
        return "Ship{" + "imoCode=" + imoCode + ", name=" + name + '}';
    }
    
    
}
