package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship entity facade. EJB example with an example of a call to a named query.
 * 
 * Fachada para el manejo de la entidad Ship. Es un EJB con un ejemplo de llamada a una consulta almacenada con nombre.
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipFacade extends CrudFacade<Ship> {
    
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
