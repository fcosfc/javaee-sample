package com.wordpress.fcosfc.betabeers.javaee.sample.repository;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ship type entity repository. EJB example with example of the Persistence Query Language.
 * 
 * Repositorio para el manejo de la entidad ShipType. Es un EJB con ejemplos del Persistence Query Language.
 * 
 * @author Paco Saucedo
 */
@Stateless
public class ShipTypesRepository extends CrudRepository<ShipType> {
    
    @PersistenceContext
    private EntityManager em;

    public ShipTypesRepository() {
        super(ShipType.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<ShipType> findAll() {
        return getEntityManager().
                createQuery("SELECT s FROM ShipType s ORDER BY s.description").
                setHint("org.hibernate.cacheable", Boolean.TRUE).
                getResultList();
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
