package com.wordpress.fcosfc.betabeers.javaee.sample.resource.rest;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.CountryDTO;
import com.wordpress.fcosfc.betabeers.javaee.sample.test.JavaEESampleRestTestDeployment;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Paco Saucedo
 */
@RunWith(Arquillian.class)
public class CountryRestResourceIntegrationTest {

    private static final String COUNTRY_ISO_CODE = "PT";
    private static final String TOO_SHORT_COUNTRY_ISO_CODE = "F";
    private static final String TOO_LONG_COUNTRY_ISO_CODE = "ABC";
    private static final String COUNTRY_NAME = "Portugal";
    private static final String COUNTRY_NAME_UPDATED = "PORTUGAL";    
    private static final String TOO_SHORT_COUNTRY_NAME = "P"; 
    private static final String TOO_LONG_COUNTRY_NAME = 
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "aaaaaaaaaa" +
            "a";
    private static final String FIRST_COUNTRY_NAME_IN_LIST = "Denmark";
    private static final int COUNTRY_LIST_SIZE = 7;
            
    @Deployment(testable = false)
    public static WebArchive deployment() {
        return JavaEESampleRestTestDeployment.deployment();
    }    
        
    @Test
    @InSequence(1)
    public void testFindAll(@ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

        Assert.assertEquals(OK, response.getStatusInfo());
        
        List<CountryDTO> countryDtoList = response.readEntity(new GenericType<List<CountryDTO>>() {});
        
        Assert.assertEquals("Wrong number of countries found", 
                COUNTRY_LIST_SIZE, 
                countryDtoList.size());
        Assert.assertEquals("Countries list in wrong order",
                FIRST_COUNTRY_NAME_IN_LIST,
                countryDtoList.get(0).getName());
    }

    @Test
    @InSequence(2)
    public void testCreate(@ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(COUNTRY_ISO_CODE, COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(CREATED, response.getStatusInfo());                
        Assert.assertNotNull(response.getHeaderString("Location"));
    }
    
    @Test
    @InSequence(3)
    public void testUpdate(@ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {                
        CountryDTO countryDTO = new CountryDTO(COUNTRY_ISO_CODE, COUNTRY_NAME_UPDATED);
        
        Response response = 
                webTarget.path("/" + COUNTRY_ISO_CODE)
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.json(countryDTO));
        
        Assert.assertEquals(OK, response.getStatusInfo());
    }
    
    @Test
    @InSequence(4)
    public void testFind(@ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        Response response = webTarget.path("/" + COUNTRY_ISO_CODE)
                        .request(MediaType.APPLICATION_JSON).get();

        Assert.assertEquals(OK, response.getStatusInfo());
        
        CountryDTO countryDTO = response.readEntity(CountryDTO.class);
        
        Assert.assertEquals("Wrong country found", 
                COUNTRY_NAME_UPDATED, 
                countryDTO.getName());
    }    
    
    @Test
    @InSequence(5)
    public void testRemove(@ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        Response response = webTarget.path("/" + COUNTRY_ISO_CODE)
                        .request(MediaType.APPLICATION_JSON).delete();
        
        Assert.assertEquals(NO_CONTENT, response.getStatusInfo());
    }
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityTooShortCountryIsoCode(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(TOO_SHORT_COUNTRY_ISO_CODE, COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    }   
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityTooLongCountryIsoCode(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(TOO_LONG_COUNTRY_ISO_CODE, COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    } 
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityTooShortCountryName(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(COUNTRY_ISO_CODE, TOO_SHORT_COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    }   
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityTooLongCountryName(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(COUNTRY_ISO_CODE, TOO_LONG_COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    } 
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityEmptyIsoCode(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO("", COUNTRY_NAME);
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    }
        
    @Test
    @InSequence(6)
    public void testCreateNotValidEntityEmptyName(
            @ArquillianResteasyResource("webresources/country") 
            ResteasyWebTarget webTarget) throws Exception {
        CountryDTO countryDTO = new CountryDTO(COUNTRY_ISO_CODE, "");
        
        Response response = 
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(countryDTO));
        
        Assert.assertEquals(BAD_REQUEST, response.getStatusInfo());   
    }

}
