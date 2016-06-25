package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType;
import com.wordpress.fcosfc.betabeers.javaee.sample.test.JavaEESampleTestDeployment;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test with Arquillian for the ShipType entity facade
 * 
 * Test de integración con Arquillian para la fachada de la entidad ShipType
 * 
 * @author Paco Saucedo
 */
@RunWith(Arquillian.class)
public class ShipTypeFacadeTest {
    
    @Inject
    private ShipTypeFacade shipTypeFacade;
    
    @Deployment
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()                
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.facade.CRUDFacade.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.facade.ShipTypeFacade.class);
    }

    @Test
    @InSequence(2)
    public void testCreate() throws Exception {
        shipTypeFacade.create(new ShipType("BLK", "Bulk Carrier"));
    }

    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {
        ShipType bulkCarrier = shipTypeFacade.find("BLK");
        bulkCarrier.setDescription("BULK CARRIER");
        shipTypeFacade.update(bulkCarrier);
    }

    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertTrue(shipTypeFacade.find("BLK").getDescription().equals("BULK CARRIER"));
    }

    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        ShipType bulkCarrier = shipTypeFacade.find("BLK");
        shipTypeFacade.remove(bulkCarrier);
        Assert.assertNull(shipTypeFacade.find("BLK"));
    }

    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertTrue(shipTypeFacade.findAll().size() == 3);
    }

    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertTrue(shipTypeFacade.findRange(new int[] {0, 1}).size() == 2);
    }

    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertTrue(shipTypeFacade.findByFilter("%").size() == 3);
        Assert.assertTrue(shipTypeFacade.findByFilter("F%").size() == 2); 
        Assert.assertTrue(shipTypeFacade.findByFilter("F%").get(0).getDescription().equals("Fast Ferry"));        
    }

    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertTrue(shipTypeFacade.count() == 3);
    }
    
}
