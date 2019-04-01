package com.wordpress.fcosfc.betabeers.javaee.sample.service;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CountriesRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CrudRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.ShipTypesRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.ShipsRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.DtoFactory;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.EntityFactory;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class ShipsService extends CrudService<Ship, ShipDTO>{

    @Inject
    private ShipsRepository shipsRepository;
    
    @Inject
    private CountriesRepository countriesRepository;
    
    @Inject
    private ShipTypesRepository shipTypesRepository;
    
    @Inject
    private EntityFactory<Ship, ShipDTO> entityFactory;
    
    @Inject
    private DtoFactory<Ship, ShipDTO> dtoFactory;
    
    @Override
    protected CrudRepository getCrudRepository() {
        return shipsRepository;
    }

    @Override
    protected EntityFactory getEntityFactory() {
        return entityFactory;
    }

    @Override
    protected DtoFactory getDtoFactory() {
        return dtoFactory;
    }

    @Override
    public ShipDTO create(ShipDTO dto) {
        Country country = 
                countriesRepository.find(dto.getFlag().getIsoCode());
        ShipType shipType =
                shipTypesRepository.find(dto.getShipType().getShipTypeCode());
        Ship ship = entityFactory.getShip(dto, country, shipType);
        
        return dtoFactory.getDTO(ship);
    }

    @Override
    public ShipDTO update(ShipDTO dto) {
        Country country = 
                countriesRepository.find(dto.getFlag().getIsoCode());
        ShipType shipType =
                shipTypesRepository.find(dto.getShipType().getShipTypeCode());
        Ship ship = shipsRepository.find(dto.getShipId());
        ship = entityFactory.updateShip(dto, ship, country, shipType);
        
        return dtoFactory.getDTO(ship);
    }        
    
}
