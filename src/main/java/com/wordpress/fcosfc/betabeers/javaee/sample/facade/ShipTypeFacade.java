package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship type entity facade. EJB example with example of the Persistence Query Language.
 * 
 * Fachada para el manejo de la entidad ShipType. Es un EJB con ejemplos del Persistence Query Language.
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipTypeFacade extends CrudFacade<ShipType> implements Serializable {
    
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
    public List<ShipType> findAll() {
        return getEntityManager().createQuery("SELECT s FROM ShipType s ORDER BY s.description").getResultList();
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
