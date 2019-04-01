package com.wordpress.fcosfc.betabeers.javaee.sample.util;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.AbstractEntity;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import javax.enterprise.context.Dependent;

/**
 * Entity factory
 * 
 * Factoría de entidades
 * 
 * @author Paco
 * @param <T>
 * @param <K>
 */
@Dependent
public class EntityFactory<T extends AbstractEntity, K extends AbstractDTO> {

    public T getEntity(K dto) {
        if (dto instanceof CountryDTO) {
            return (T) getCountry((CountryDTO) dto);
        } else if (dto instanceof ShipTypeDTO) {
            return (T) getShipType((ShipTypeDTO) dto);
        } else {
            throw new UnsupportedOperationException();
        }
    }
    
    public Ship getShip(ShipDTO shipDTO, Country country, ShipType shipType) {
        return updateShip(shipDTO, new Ship(), country, shipType);
    }
    
    public T updateEntity(T entity, K dto) {
        if (dto instanceof CountryDTO) {
            return (T) updateCountry((Country) entity, (CountryDTO) dto);
        } else if (dto instanceof ShipTypeDTO) {
            return (T) updateShipType((ShipType) entity, (ShipTypeDTO) dto);
        } else {
            throw new UnsupportedOperationException();
        }
    }
    
    public Ship updateShip(ShipDTO shipDTO, Ship ship, Country country, ShipType shipType) {
        ship.setImoCode(shipDTO.getImoCode());
        ship.setName(shipDTO.getName());
        ship.setGrossTons(shipDTO.getGrossTons());
        ship.setDateBuilt(shipDTO.getDateBuilt());
        ship.setFlag(country);
        ship.setShipType(shipType);
        
        return ship;
    }

    private Country getCountry(CountryDTO countryDTO) {
        return new Country(countryDTO.getIsoCode(), countryDTO.getName());
    }

    private ShipType getShipType(ShipTypeDTO shipTypeDTO) {
        return new ShipType(shipTypeDTO.getShipTypeCode(),
                shipTypeDTO.getDescription());
    }
    
    private Country updateCountry(Country country, CountryDTO countryDTO) {
        country.setName(countryDTO.getName());
        
        return country;
    }
    
    private ShipType updateShipType(ShipType shipType, ShipTypeDTO shipTypeDTO) {
        shipType.setDescription(shipTypeDTO.getDescription());
        
        return shipType;
    }

}
