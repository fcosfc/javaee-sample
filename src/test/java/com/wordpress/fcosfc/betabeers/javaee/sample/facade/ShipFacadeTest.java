package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship;
import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.test.JavaEESampleTestDeployment;
import java.util.Calendar;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test with Arquillian for the Ship entity facade
 * 
 * Test de integración con Arquillian para la fachada de la entidad Ship
 * 
 * @author Paco Saucedo
 */
@RunWith(Arquillian.class)
public class ShipFacadeTest {
    
    @Inject
    private ShipFacade shipFacade;
    
    @Inject
    private ShipTypeFacade shipTypeFacade;
    
    @Inject
    private CountryFacade countryFacade;
    
    @Deployment
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()                
                .addPackage(com.wordpress.fcosfc.betabeers.javaee.sample.entity.Ship.class.getPackage())
                .addPackage(com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade.class.getPackage());
    }

    @Test
    @InSequence(2)
    public void testCreate() throws Exception {
        ShipType containerShip = shipTypeFacade.find("CON");
        Country spain = countryFacade.find("ES");        
        shipFacade.create(new Ship(9703291, 
                "MSC Oscar",
                192237,
                Calendar.getInstance().getTime(),
                spain,
                containerShip
        ));
    }

    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {
        Ship albaycin = shipFacade.find(new Long(-2));
        albaycin.setName("ALBAYCIN");
        shipFacade.update(albaycin);
    }

    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertTrue(shipFacade.find(new Long(-2)).getName().equals("ALBAYCIN"));
    }

    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        Ship albaycin = shipFacade.find(new Long(-2));
        shipFacade.remove(albaycin);
        Assert.assertNull(shipFacade.find(new Long(-2)));
    }

    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertTrue(shipFacade.findAll().size() == 5);
    }

    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertTrue(shipFacade.findRange(new int[] {0, 2}).size() == 3);
    }

    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertTrue(shipFacade.findByFilter("%").size() == 5);
        Assert.assertTrue(shipFacade.findByFilter("%M%").size() == 3); 
        Assert.assertTrue(shipFacade.findByFilter("%M%").get(0).getName().equals("Caroline Maersk"));        
    }

    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertTrue(shipFacade.count() == 5);
    }
    
}
