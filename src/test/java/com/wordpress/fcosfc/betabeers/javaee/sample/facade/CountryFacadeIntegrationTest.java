package com.wordpress.fcosfc.betabeers.javaee.sample.facade;

import com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country;
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
 * Integration test with Arquillian for the Country entity facade
 * 
 * Test de integración con Arquillian para la fachada de la entidad Country
 * 
 * @author Paco Saucedo
 */
@RunWith(Arquillian.class)
public class CountryFacadeIntegrationTest {

    private static final String COUNTRY_ISO_CODE = "PT";
    private static final String COUNTRY_NAME = "Portugal";
    private static final String COUNTRY_NAME_UPDATED = "PORTUGAL";
    private static final String FIRST_COUNTRY_NAME_IN_LIST = "Denmark";
    private static final String FIRST_COUNTRY_NAME_IN_FILTERED_LIST = "United Kingdom";
    private static final String FILTER = "United%";
    private static final Integer COUNTRY_LIST_SIZE = 7;
    private static final Integer RANGE_COUNTRY_LIST_SIZE = 3;
    private static final Integer FILTERED_COUNTRY_LIST_SIZE = 2;
    
    @Inject
    private CountryFacade countryFacade;
    
    @Deployment
    public static WebArchive deployment() {
        return JavaEESampleTestDeployment.deployment()                
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.entity.Country.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.facade.CrudFacade.class)
                .addClass(com.wordpress.fcosfc.betabeers.javaee.sample.facade.CountryFacade.class);
    }
    
    @Test
    @InSequence(2)
    public void testCreate() throws Exception {
        countryFacade.create(new Country(COUNTRY_ISO_CODE, COUNTRY_NAME));
    }
    
    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {        
        Country portugal = countryFacade.find(COUNTRY_ISO_CODE);
        portugal.setName(COUNTRY_NAME_UPDATED);
        countryFacade.update(portugal);
    }
    
    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertEquals("Wrong country found", 
                COUNTRY_NAME_UPDATED, 
                countryFacade.find(COUNTRY_ISO_CODE).getName());
    }
    
    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        Country portugal = countryFacade.find(COUNTRY_ISO_CODE);
        countryFacade.remove(portugal);
        Assert.assertEquals("No country expected after removing the item", 
                null, 
                countryFacade.find(COUNTRY_ISO_CODE));
    }
    
    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertEquals("Wrong number of countries found", 
                COUNTRY_LIST_SIZE, 
                (Integer) countryFacade.findAll().size());
        Assert.assertEquals("Countries list in wrong order",
                FIRST_COUNTRY_NAME_IN_LIST,
                countryFacade.findAll().get(0).getName());
    }
    
    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertEquals("Wrong number of coutries found in range query",
                RANGE_COUNTRY_LIST_SIZE, 
                (Integer) countryFacade.findRange(new int[] {2, 4}).size());
    }
    
    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertEquals("Wrong number of countries found in no filter query",
                COUNTRY_LIST_SIZE, 
                (Integer) countryFacade.findByFilter("%").size());
        Assert.assertEquals("Wrong number of countries found in filtered query",
                FILTERED_COUNTRY_LIST_SIZE,
                (Integer) countryFacade.findByFilter(FILTER).size()); 
        Assert.assertEquals("Wrong order of countries found in filtered query",
                FIRST_COUNTRY_NAME_IN_FILTERED_LIST, 
                countryFacade.findByFilter(FILTER).get(0).getName());        
    }
    
    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertEquals("Wrong count of countries",
                COUNTRY_LIST_SIZE,
                (Integer) countryFacade.count());
    }

}
