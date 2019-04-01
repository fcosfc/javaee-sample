package com.wordpress.fcosfc.betabeers.javaee.sample.dto;

import com.wordpress.fcosfc.betabeers.javaee.sample.validation.ImoCode;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Ship entity with bean validations
 * 
 * DTO para la entidad Buque con validaciones
 * 
 * @author Paco Saucedo
 */
public class ShipDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 3153378847137216223L;    
    
    private Long shipId;
    
    @NotNull
    @ImoCode
    private Integer imoCode;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @NotNull
    @Min(1)
    @Max(200000)
    private Integer grossTons;
    
    @Past
    private Date dateBuilt;
    
    private CountryDTO flag;
    
    private ShipTypeDTO shipType;

    public ShipDTO() {
    }
    
    public ShipDTO(Long shipId, Integer imoCode, String name, Integer grossTons, Date dateBuilt, CountryDTO flag, ShipTypeDTO shipType) {
        super(shipId == null ? null : shipId.toString());
        this.shipId = shipId;
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

    public CountryDTO getFlag() {
        return flag;
    }

    public void setFlag(CountryDTO flag) {
        this.flag = flag;
    }    

    public ShipTypeDTO getShipType() {
        return shipType;
    }
    
    public void setShipType(ShipTypeDTO shipType) {
        this.shipType = shipType;
    }        

    @Override
    public int hashCode() {
        return 163 + this.imoCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final ShipDTO other = (ShipDTO) obj;
            
            return this.imoCode.equals(other.imoCode);
        }
    }   

    @Override
    public String toString() {
        return "ShipDTO{" + "shipId=" + shipId + ", imoCode=" + imoCode + ", name=" + name + ", grossTons=" + grossTons + ", dateBuilt=" + dateBuilt + ", flag=" + flag + ", shipType=" + shipType + '}';
    }    
    
}
