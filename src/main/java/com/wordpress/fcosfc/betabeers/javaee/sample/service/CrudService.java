package com.wordpress.fcosfc.betabeers.javaee.sample.service;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.AbstractEntity;
import com.wordpress.fcosfc.betabeers.javaee.sample.repository.CrudRepository;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.DtoFactory;
import com.wordpress.fcosfc.betabeers.javaee.sample.util.EntityFactory;
import java.util.List;

/**
 * Abstract service
 * 
 * Servicio abstracto
 * 
 * @author Paco Saucedo
 * @param <T>
 * @param <K>
 */
public abstract class CrudService<T extends AbstractEntity, K extends AbstractDTO> {
    
    protected abstract CrudRepository getCrudRepository();
    
    protected abstract EntityFactory getEntityFactory();
    
    protected abstract DtoFactory getDtoFactory();
    
    public K create(K dto) {
        return (K) getDtoFactory()
                .getDTO((AbstractEntity) getCrudRepository()
                        .create(getEntityFactory().getEntity(dto)));
    }

    public K update(K dto) {
        T savedEntity = (T) getCrudRepository().find(dto.getId());
        
        
        return (K) getDtoFactory()
                .getDTO((AbstractEntity) getCrudRepository()
                        .update(getEntityFactory()
                                .updateEntity(savedEntity, dto)));
    }

    public void remove(Object id) {
        getCrudRepository()
                .remove((T) getCrudRepository().find(id));
    }

    public K find(Object id) {
        return (K) getDtoFactory()
                .getDTO((AbstractEntity) getCrudRepository().find(id));
    }

    public List<K> findAll() {
        return getDtoFactory().getDtoList(getCrudRepository().findAll());
    }

    public List<K> findRange(int[] range) {
        return getDtoFactory().getDtoList(getCrudRepository().findRange(range));
    }

    public int count() {
        return getCrudRepository().count();
    }
    
    public List<K> findByFilter(String filter) {
        return getDtoFactory().getDtoList(getCrudRepository().findByFilter(filter));
    }  
    
}
