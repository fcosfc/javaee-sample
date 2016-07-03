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
public class CountryFacadeTest {

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
        countryFacade.create(new Country("PT", "Portugal"));
    }
    
    @Test
    @InSequence(3)
    public void testUpdate() throws Exception {        
        Country portugal = countryFacade.find("PT");
        portugal.setName("PORTUGAL");
        countryFacade.update(portugal);
    }
    
    @Test
    @InSequence(4)
    public void testFind() throws Exception {
        Assert.assertTrue(countryFacade.find("PT").getName().equals("PORTUGAL"));
    }
    
    @Test
    @InSequence(5)
    public void testRemove() throws Exception {
        Country portugal = countryFacade.find("PT");
        countryFacade.remove(portugal);
        Assert.assertNull(countryFacade.find("PT"));
    }
    
    @Test
    @InSequence(1)
    public void testFindAll() throws Exception {
        Assert.assertTrue(countryFacade.findAll().size() == 7);
        Assert.assertTrue(countryFacade.findAll().get(0).getName().equals("Denmark"));
    }
    
    @Test
    @InSequence(1)
    public void testFindRange() throws Exception {
        Assert.assertTrue(countryFacade.findRange(new int[] {2, 4}).size() == 3);
    }
    
    @Test
    @InSequence(1)
    public void testFindByFilter() throws Exception {
        Assert.assertTrue(countryFacade.findByFilter("%").size() == 7);
        Assert.assertTrue(countryFacade.findByFilter("United%").size() == 2); 
        Assert.assertTrue(countryFacade.findByFilter("United%").get(0).getName().equals("United Kingdom"));        
    }
    
    @Test
    @InSequence(1)
    public void testCount() throws Exception {
        Assert.assertTrue(countryFacade.count() == 7);
    }

}
