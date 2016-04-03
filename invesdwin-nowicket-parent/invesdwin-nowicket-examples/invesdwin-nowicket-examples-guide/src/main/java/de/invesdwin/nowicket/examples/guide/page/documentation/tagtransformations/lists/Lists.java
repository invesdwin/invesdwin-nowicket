package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.lists;

import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class Lists extends AValueObject {

    private TimeUnit defaultSelect;
    private TimeUnit asList;
    private TimeUnit[] asMultiselectList;
    private TimeUnit[] asMultiselectPalette;
    private TimeUnit[] asTable;

    public TimeUnit getDefaultSelect() {
        return defaultSelect;
    }

    public void setDefaultSelect(final TimeUnit defaultSelect) {
        this.defaultSelect = defaultSelect;
    }

    public TimeUnit getAsList() {
        return asList;
    }

    public void setAsList(final TimeUnit asList) {
        this.asList = asList;
    }

    public TimeUnit[] getAsMultiselectList() {
        return asMultiselectList;
    }

    public void setAsMultiselectList(final TimeUnit[] asMultiselectList) {
        this.asMultiselectList = asMultiselectList;
    }

    public TimeUnit[] getAsMultiselectPalette() {
        return asMultiselectPalette;
    }

    public void setAsMultiselectPalette(final TimeUnit[] asMultiselectPalette) {
        this.asMultiselectPalette = asMultiselectPalette;
    }

    public TimeUnit[] getAsTable() {
        return asTable;
    }

    public void setAsTable(final TimeUnit[] asTable) {
        this.asTable = asTable;
    }

}
