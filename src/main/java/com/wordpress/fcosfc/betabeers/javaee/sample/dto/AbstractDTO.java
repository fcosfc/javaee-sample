package com.wordpress.fcosfc.betabeers.javaee.sample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author Paco Saucedo
 */
public class AbstractDTO implements Serializable {

    private static final long serialVersionUID = -3343025580883921471L;
    
    @JsonIgnore
    private String id;

    public AbstractDTO() {
    }
    
    public AbstractDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
                
}
