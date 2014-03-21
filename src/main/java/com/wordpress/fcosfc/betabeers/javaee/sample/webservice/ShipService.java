package com.wordpress.fcosfc.betabeers.javaee.sample.webservice;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Ship entity SOAP WS facade. It's a stateless EJB.
 * 
 * Fachada del Servicio Web SOAP para la entidad Ship.
 * 
 * @author Paco Saucedo
 */
@WebService
@Stateless
public class ShipService {
    
    @Inject
    protected ShipFacade shipFacade;

    public ShipService() {
    }
    
    @WebMethod
    public void create(@WebParam(name = "entity") Ship entity) {
        shipFacade.create(entity);
    }
    
    @WebMethod
    public void edit(@WebParam(name = "id") Long id, @WebParam(name = "entity") Ship entity) {
        shipFacade.update(entity);
    }
    
    @WebMethod
    public void remove(@WebParam(name = "id") Long id) {
        shipFacade.remove(shipFacade.find(id));
    }
    
    @WebMethod
    public Ship find(@WebParam(name = "id") Long id) {
        return shipFacade.find(id);
    }
    
    @WebMethod
    public List<Ship> findAll() {
        return shipFacade.findAll();
    }
    
    @WebMethod
    public List<Ship> findRange(@WebParam(name = "from") Integer from, @WebParam(name = "to") Integer to) {
        return shipFacade.findRange(new int[]{from, to});
    }
    
    @WebMethod
    public Integer countREST() {
        return shipFacade.count();
    }
}
