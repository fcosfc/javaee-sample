package com.wordpress.fcosfc.betabeers.javaee.sample.webservice;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Country entity SOAP WS facade. It's a stateless EJB.
 * 
 * Fachada del Servicio Web SOAP para la entidad Country.
 * 
 * @author Paco Saucedo
 */
@WebService
@Stateless
public class CountryService {

    @Inject
    protected CountryFacade countryFacade;
    
    public CountryService() {        
    }
    
    @WebMethod    
    public void create(@WebParam(name = "entity") Country entity) {
        countryFacade.create(entity);
    }
    
    @WebMethod
    public void edit(@WebParam(name = "id") String id, @WebParam(name = "entity") Country entity) {
        countryFacade.update(entity);
    }
    
    @WebMethod
    public void remove(@WebParam(name = "id") String id) {
        countryFacade.remove(countryFacade.find(id));
    }
    
    @WebMethod
    public Country find(@WebParam(name = "id") String id) {
        return countryFacade.find(id);
    }
    
    @WebMethod
    public List<Country> findAll() {
        return countryFacade.findAll();
    }
    
    @WebMethod
    public List<Country> findRange(@WebParam(name = "from") Integer from, @WebParam(name = "to") Integer to) {
        return countryFacade.findRange(new int[]{from, to});
    }
    
    @WebMethod
    public Integer count() {
        return countryFacade.count();
    }
}
