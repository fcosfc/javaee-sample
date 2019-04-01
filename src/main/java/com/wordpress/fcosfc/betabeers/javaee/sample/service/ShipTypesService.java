package com.wordpress.fcosfc.betabeers.javaee.sample.service;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.ShipTypeDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CrudRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.ShipTypesRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.DtoFactory;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.EntityFactory;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class ShipTypesService extends CrudService<ShipType, ShipTypeDTO> {

    @Inject
    private ShipTypesRepository repository;
    
    @Inject
    private EntityFactory<ShipType, ShipTypeDTO> entityFactory;
    
    @Inject
    private DtoFactory<ShipType, ShipTypeDTO> dtoFactory;
    
    @Override
    protected CrudRepository getCrudRepository() {
        return repository;
    }

    @Override
    protected EntityFactory getEntityFactory() {
        return entityFactory;
    }

    @Override
    protected DtoFactory getDtoFactory() {
        return dtoFactory;
    }        
    
}
