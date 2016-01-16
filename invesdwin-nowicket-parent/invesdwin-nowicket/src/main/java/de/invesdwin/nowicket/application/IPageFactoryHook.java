package de.invesdwin.nowicket.application;

/**
 * This interface is only supported on Page classes.
 */
public interface IPageFactoryHook {

    /**
     * This method gets called on page creation or cache retrieval. It can be used to refresh the page.
     * 
     * The newModelObject is the one with which the request was made. It can be decided to copy values from it instead
     * of keeping the current model as it is or replace it entirely with the new one. The default behaviour though is to
     * keep the old model as it is.
     */
    void onPageModelRefresh(Object newModelObject);

}
