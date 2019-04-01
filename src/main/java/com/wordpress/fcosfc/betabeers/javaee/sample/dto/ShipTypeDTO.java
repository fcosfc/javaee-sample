package com.wordpress.fcosfc.betabeers.javaee.sample.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for ShipType entity
 * 
 * DTO para la entidad Tipo de Buque
 * 
 * @author Paco Saucedo
 */
public class ShipTypeDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 6213570854298703320L;
    
    @NotNull
    @Size(min = 3, max = 5)
    private String shipTypeCode;    
    
    @NotNull
    @Size(min = 2, max = 255)
    private String description;

    public ShipTypeDTO() {
    }

    public ShipTypeDTO(String shipTypeCode, String description) {
        super(shipTypeCode);
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
        return 122 + this.shipTypeCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final ShipTypeDTO other = (ShipTypeDTO) obj;
            
            return this.shipTypeCode.equals(other.shipTypeCode);
        }
    }   

    @Override
    public String toString() {
        return "ShipTypeDTO{" + "shipTypeCode=" + shipTypeCode + ", description=" + description + '}';
    }
        
}
