package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Country entity facade
 * 
 * @author Paco Saucedo
 */
@Stateless
public class CountryFacade extends CRUDFacade<Country> implements Serializable{
    
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
    
    public List<Country> findByFilter(String filter) {
        return getEntityManager().createQuery("SELECT c "
                + "FROM Country c "
                + "WHERE upper(c.name) like upper(:nameFilter) "
                + "ORDER BY c.name")
                .setParameter("nameFilter", filter)
                .getResultList();
    }
}
