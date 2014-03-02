package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Country entity facade
 * 
 * @author Paco Saucedo
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> implements Serializable{
    
    @PersistenceContext
    private EntityManager em;

    public CountryFacade() {
        super(Country.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Country> findAll() {
        return getEntityManager().createQuery("SELECT c FROM Country c ORDER BY c.name").getResultList();
    }
}
