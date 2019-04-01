package com.wordpress.fcosfc.betabeers.javaee.sample.repository;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship entity repositorio. EJB example with an example of a call to a named query.
 * 
 * Repositorio para el manejo de la entidad Ship. Es un EJB con un ejemplo de llamada a una consulta almacenada con nombre.
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipsRepository extends CrudRepository<Ship> {
    
    @PersistenceContext
    private EntityManager em;

    public ShipsRepository() {
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
