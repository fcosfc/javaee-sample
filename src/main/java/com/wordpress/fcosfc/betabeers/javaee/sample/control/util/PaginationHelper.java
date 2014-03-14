package com.wordpress.fcosfc.betabeers.javaee.sample.control.util;

import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Helper JSF presentation class for pagination.
 * 
 * Clase de ayuda para la presentación JSF en el campo de la paginación.
 * 
 * @author Paco Saucedo
 */
public class PaginationHelper<T> {
    
    private final static int PAGE_SIZE = 5;
    
    private final Class<T> entityClass;
    private List<T> entities;
    private DataModel<T> currentPage;
    private int currentPageIndex, pageCount;

    public PaginationHelper(Class<T> entityClass, List<T> entities, int currentPageIndex) {
        this.entityClass = entityClass;
        this.entities = entities;
        this.currentPageIndex = currentPageIndex;
        pageCount = ((entities.size() - 1) / PAGE_SIZE) + 1;        
        if (this.currentPageIndex > pageCount) {
            this.currentPageIndex = pageCount;
        }
        refresh();
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }        
    
    public DataModel<T> getCurrentPage() {
        return currentPage;
    }
    
    public boolean isHasNextPage() {
        return currentPageIndex < pageCount;
    }
    
    public void nextPage() {
        if (isHasNextPage()) {
            currentPageIndex++;
            refresh();
        }
    }
    
    public boolean isHasPreviousPage() {
        return currentPageIndex > 1;
    }

    public void previousPage() {
        if (isHasPreviousPage()) {
            currentPageIndex--;
            refresh();
        }
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }
    
    private void refresh() {
        int lastIndex = ((currentPageIndex - 1) * PAGE_SIZE) + PAGE_SIZE;
        
        if (lastIndex > entities.size()) {
            lastIndex = entities.size();
        } 
                      
        currentPage = new ListDataModel<T>(entities.subList((currentPageIndex - 1) * PAGE_SIZE, lastIndex));
    }
}
