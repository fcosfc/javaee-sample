package com.wordpress.fcosfc.betabeers.javaee.sample.repository;

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
public class ShipTypesRepositoryIntegrationTest {
    
    private static final String SHIP_TYPE_CODE = "BLK";
    private static final String SHIP_TYPE_DESCRIPTION = "Bulk Carrier";
    private static final String SHIP_TYPE_DESCRIPTION_UPDATED = "BULK CARRIER";
    private static final String FILTER = "F%";
    private static final String FIRST_SHIP_TYPE_IN_FILTERED_LIST = "Fast Ferry";
    private static final Integer SHIP_TYPE_LIST_SIZE = 3;
    private static final Integer RANGE_SHIP_TYPE_LIST_SIZE = 2;
    private static final Integer FILTERED_SHIP_TYPE_LIST_SIZE = 2;
    
    @Inject
    private ShipTypesRepository shipTypesRepository;
    
    @Deployment
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()                
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.entity.ShipType.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.repository.ShipTypesRepository.class);
    }

    @Test
    @InSequence(2)
    public void testCreate() throws Exception {
        shipTypesRepository.create(new ShipType(SHIP_TYPE_CODE, SHIP_TYPE_DESCRIPTION));
    }

    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {
        ShipType bulkCarrier = shipTypesRepository.find(SHIP_TYPE_CODE);
        bulkCarrier.setDescription(SHIP_TYPE_DESCRIPTION_UPDATED);
        shipTypesRepository.update(bulkCarrier);
    }

    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertEquals("Wrong ship type found",
                SHIP_TYPE_DESCRIPTION_UPDATED,
                shipTypesRepository.find(SHIP_TYPE_CODE).getDescription());
    }

    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        ShipType bulkCarrier = shipTypesRepository.find(SHIP_TYPE_CODE);
        shipTypesRepository.remove(bulkCarrier);
        Assert.assertEquals("No ship type expected after removing the item",
                null,
                shipTypesRepository.find(SHIP_TYPE_CODE));
    }

    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertEquals("Wrong number of ship types found", 
                SHIP_TYPE_LIST_SIZE,
                (Integer) shipTypesRepository.findAll().size());
    }

    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertEquals("Wrong number of ship types found in range query",
                RANGE_SHIP_TYPE_LIST_SIZE,
                (Integer) shipTypesRepository.findRange(new int[] {0, 1}).size());
    }

    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertEquals("Wrong number of ship types found in no filter query",
                SHIP_TYPE_LIST_SIZE,
                (Integer) shipTypesRepository.findByFilter("%").size());
        Assert.assertEquals("Wrong number of ship types found in filtered query",
                FILTERED_SHIP_TYPE_LIST_SIZE,
                (Integer) shipTypesRepository.findByFilter(FILTER).size()); 
        Assert.assertEquals("Wrong order of ship types found in filtered query",
                FIRST_SHIP_TYPE_IN_FILTERED_LIST,
                shipTypesRepository.findByFilter(FILTER).get(0).getDescription());        
    }

    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertEquals("Wrong count of ship types",
                SHIP_TYPE_LIST_SIZE,
                (Integer) shipTypesRepository.count());
    }
    
}
