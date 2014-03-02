package com.wordpress.fcosfc.betabeers.javaee.sample.control.util;

import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Paco Saucedo
 */
public class PaginationHelper<T> {
    
    private Class<T> entityClass;
    private List<T> entities;
    private DataModel<T> currentPage;
    private int pageSize;
    private int currentPageIndex;
    private int pageCount;

    public PaginationHelper(Class<T> entityClass, List<T> entities, int currentPageIndex) {
        this.entityClass = entityClass;
        this.entities = entities;
        this.currentPageIndex = currentPageIndex;
        pageSize = 5;
        pageCount = ((entities.size() - 1) / pageSize) + 1;        
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

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }
    
    private void refresh() {
        int lastIndex = ((currentPageIndex - 1) * pageSize) + pageSize;
        
        if (lastIndex > entities.size()) {
            lastIndex = entities.size();
        } 
                      
        currentPage = new ListDataModel<T>(entities.subList((currentPageIndex - 1) * pageSize, lastIndex));
    }
}
