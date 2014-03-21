package com.wordpress.fcosfc.betabeers.javaee.sample.webservice;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * ShipType entity SOAP WS facade. It's a stateless EJB.
 * 
 * Fachada del Servicio Web SOAP para la entidad ShipType.
 * 
 * @author Paco Saucedo
 */
@WebService
@Stateless
public class ShipTypeService {
    
    @Inject
    protected ShipTypeFacade shipTypeFacade;

    public ShipTypeService() {
    }

    @WebMethod
    public void create(@WebParam(name = "entity") ShipType entity) {
        shipTypeFacade.create(entity);
    }

    @WebMethod
    public void edit(@WebParam(name = "id") String id, @WebParam(name = "entity") ShipType entity) {
        shipTypeFacade.update(entity);
    }

    @WebMethod
    public void remove(@WebParam(name = "id") String id) {
        shipTypeFacade.remove(shipTypeFacade.find(id));
    }

    @WebMethod
    public ShipType find(@WebParam(name = "id") String id) {
        return shipTypeFacade.find(id);
    }

    @WebMethod
    public List<ShipType> findAll() {
        return shipTypeFacade.findAll();
    }

    @WebMethod
    public List<ShipType> findRange(@WebParam(name = "from") Integer from, @WebParam(name = "to") Integer to) {
        return shipTypeFacade.findRange(new int[]{from, to});
    }

    @WebMethod
    public Integer countREST() {
        return shipTypeFacade.count();
    }
}
