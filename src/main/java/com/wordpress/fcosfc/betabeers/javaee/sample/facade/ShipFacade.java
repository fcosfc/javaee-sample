package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship entity facade
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipFacade extends CRUDFacade<Ship> implements Serializable{
    
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
        return em.createNamedQuery("Ship.FindByNameFilter")
                .setParameter("nameFilter", filter)
                .getResultList();
    }
    
}
