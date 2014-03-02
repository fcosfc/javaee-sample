package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship type entity facade
 * 
 * @author Paco Saucedo
 */
public class ShipTypeFacade extends AbstractFacade<ShipType> {
    
    @PersistenceContext
    private EntityManager em;

    public ShipTypeFacade() {
        super(ShipType.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
