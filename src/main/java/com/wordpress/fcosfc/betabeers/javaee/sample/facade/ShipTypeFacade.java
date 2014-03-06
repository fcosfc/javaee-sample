package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship type entity facade
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipTypeFacade extends CRUDFacade<ShipType> implements Serializable {
    
    @PersistenceContext
    private EntityManager em;

    public ShipTypeFacade() {
        super(ShipType.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<ShipType> findByFilter(String filter) {
        return getEntityManager().createQuery("SELECT s "
                + "FROM ShipType s "
                + "WHERE upper(s.description) like upper(:descriptionFilter) "
                + "ORDER BY s.description")
                .setParameter("descriptionFilter", filter)
                .getResultList();
    }
    
}
