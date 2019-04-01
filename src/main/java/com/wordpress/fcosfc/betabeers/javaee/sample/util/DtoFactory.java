package com.wordpress.fcosfc.betabeers.javaee.sample.util;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.AbstractEntity;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;

/**
 * DTO factory
 * 
 * Factoría de DTOs
 * 
 * @author Paco Saucedo
 * @param <T>
 * @param <K>
 */
@Dependent
public class DtoFactory<T extends AbstractEntity, K extends AbstractDTO> {
    
    public K getDTO(T entity) {
        if (entity instanceof Country) {
            return (K) getCountryDTO((Country) entity);
        } else if (entity instanceof Ship) {
            return (K) getShipDTO((Ship) entity);
        } else if (entity instanceof ShipType) {
            return (K) getShipTypeDTO((ShipType) entity);
        } else {
            throw new UnsupportedOperationException();
        }
    }
    
    public List<K> getDtoList(List<T> entityList) {
        List<K> result = new ArrayList<>(entityList.size());
        
        entityList.forEach((entity) -> {
            result.add(getDTO(entity));
        });
        
        return result;
    }
    
    private CountryDTO getCountryDTO(Country country) {
        return new CountryDTO(country.getIsoCode(), country.getName());
    }
    
    private ShipDTO getShipDTO(Ship ship) {
        return new ShipDTO(ship.getShipId(), 
                ship.getImoCode(), 
                ship.getName(), 
                ship.getGrossTons(), 
                ship.getDateBuilt(), 
                getCountryDTO(ship.getFlag()), 
                getShipTypeDTO(ship.getShipType()));
    }
    
    private ShipTypeDTO getShipTypeDTO(ShipType shipType) {
        return new ShipTypeDTO(shipType.getShipTypeCode(), shipType.getDescription());
    }
    
}
