package com.wordpress.fcosfc.betabeers.javaee.sample.repository;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Country entity repository. EJB example with example of the Persistence Query Language.
 * 
 * Repositorio para el manejo de la entidad Country. Es un EJB con ejemplos del Persistence Query Language.
 * 
 * @author Paco Saucedo
 */
@Stateless
public class CountriesRepository extends CrudRepository<Country> {
    
    @PersistenceContext
    private EntityManager em;

    public CountriesRepository() {
        super(Country.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Country> findAll() {
        return getEntityManager().
                createQuery("SELECT c FROM Country c ORDER BY c.name").
                setHint("org.hibernate.cacheable", Boolean.TRUE).
                getResultList();
    }
    
    @Override
    public List<Country> findByFilter(String filter) {
        return getEntityManager().createQuery("SELECT c "
                + "FROM Country c "
                + "WHERE upper(c.name) like upper(:nameFilter) "
                + "ORDER BY c.name")
                .setParameter("nameFilter", filter)
                .getResultList();
    }
}
