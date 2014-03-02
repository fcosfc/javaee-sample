package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship entity facade
 * 
 * @author Paco Saucedo
 */
public class ShipFacade extends AbstractFacade<Ship> {
    
    @PersistenceContext
    private EntityManager em;

    public ShipFacade() {
        super(Ship.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
