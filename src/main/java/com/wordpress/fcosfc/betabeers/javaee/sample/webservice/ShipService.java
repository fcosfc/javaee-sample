package com.wordpress.fcosfc.betabeers.javaee.sample.webservice;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
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
    public void create(Ship entity) {
        shipFacade.create(entity);
    }
    
    @WebMethod
    public void edit(Long id, Ship entity) {
        shipFacade.update(entity);
    }
    
    @WebMethod
    public void remove(Long id) {
        shipFacade.remove(shipFacade.find(id));
    }
    
    @WebMethod
    public Ship find(Long id) {
        return shipFacade.find(id);
    }
    
    @WebMethod
    public List<Ship> findAll() {
        return shipFacade.findAll();
    }
    
    @WebMethod
    public List<Ship> findRange(Integer from, Integer to) {
        return shipFacade.findRange(new int[]{from, to});
    }
    
    @WebMethod
    public Integer countREST() {
        return shipFacade.count();
    }
}
