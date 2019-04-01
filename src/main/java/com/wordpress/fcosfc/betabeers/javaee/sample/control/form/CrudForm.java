package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import com.wordpress.fcosfc.betabeers.javaee.sample.dto.AbstractDTO;
import java.util.List;

/**
 * Abstract data structure for a CRUD form
 * 
 * Estructura de datos abstracta para un formulario CRUD
 * 
 * @author Paco Saucedo
 * @param <K>
 */
public abstract class CrudForm<K extends AbstractDTO> {
    
    private List<K> elements;
    private List<K> filteredElements;
    private K currentEntity;
    private boolean creating;
    private boolean editing;

    public List<K> getElements() {
        return elements;
    }

    public void setElements(List<K> elements) {
        this.elements = elements;
    }

    public List<K> getFilteredElements() {
        return filteredElements;
    }

    public void setFilteredElements(List<K> filteredElements) {
        this.filteredElements = filteredElements;
    }

    public K getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(K currentEntity) {
        this.currentEntity = currentEntity;
    }

    public boolean isCreating() {
        return creating;
    }

    public void setCreating(boolean creating) {
        this.creating = creating;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
        
}
