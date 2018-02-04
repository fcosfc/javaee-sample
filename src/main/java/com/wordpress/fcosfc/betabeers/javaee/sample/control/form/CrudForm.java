package com.wordpress.fcosfc.betabeers.javaee.sample.control.form;

import java.util.List;

/**
 * Abstract data structure for a CRUD form
 * 
 * Estructura de datos abstracta para un formulario CRUD
 * 
 * @author Paco Saucedo
 * @param <T>
 */
public abstract class CrudForm<T> {
    
    private List<T> elements;
    private List<T> filteredElements;
    private T currentEntity;
    private boolean creating;
    private boolean editing;

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getFilteredElements() {
        return filteredElements;
    }

    public void setFilteredElements(List<T> filteredElements) {
        this.filteredElements = filteredElements;
    }

    public T getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(T currentEntity) {
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
