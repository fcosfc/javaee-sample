package com.wordpress.fcosfc.betabeers.javaee.sample.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Country entity
 * 
 * DTO para la entidad Pais
 * 
 * @author Paco Saucedo
 */
public class CountryDTO extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 2739434345606840601L;
    
    @NotNull
    @Size(min = 2, max = 2)
    private String isoCode;
    
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    public CountryDTO() {
        super();
    }
    
    public CountryDTO(String isoCode, String name) {
        super(isoCode);
        this.isoCode = isoCode;
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  
    
    @Override
    public int hashCode() {
        return 140 + this.isoCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final CountryDTO other = (CountryDTO) obj;
            
            return this.isoCode.equals(other.isoCode);
        }
    } 

    @Override
    public String toString() {
        return "CountryDTO{" + "isoCode=" + isoCode + ", name=" + name + '}';
    }        
    
}
