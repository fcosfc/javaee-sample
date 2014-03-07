package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship entity facade
 * 
 * @author Paco Saucedo
 */
public class ShipFacade extends CRUDFacade<Ship> {
    
    @PersistenceContext
    private EntityManager em;

    public ShipFacade() {
        super(Ship.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Ship> findByFilter(String filter) {
        return em.createNamedQuery("Ship.FindByName")
                .setParameter("nameFilter", filter)
                .getResultList();
    }
    
    public List<Country> getAllCountries() {
        return em.createQuery("SELECT c FROM Country c ORDER BY c.name").getResultList();
    }
    
    public List<ShipType> getAllShipTypes() {
        return em.createNamedQuery("SELECT s FROM ShipType s ORDER BY s.description").getResultList();
    }
    
}
