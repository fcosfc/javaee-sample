package com.wordpress.fcosfc.betabeers.javaee.sample.service;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CountriesRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CrudRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.DtoFactory;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.EntityFactory;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paco Saucedo
 */
@Stateless
public class CountriesService extends CrudService<Country, CountryDTO> {

    @Inject
    private CountriesRepository repository;
    
    @Inject
    private EntityFactory<Country, CountryDTO> entityFactory;
    
    @Inject
    private DtoFactory<Country, CountryDTO> dtoFactory;
    
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
