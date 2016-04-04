package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.listsandchoices;

import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class ListsAndChoices extends AValueObject {

    private TimeUnit defaultSelect;
    private TimeUnit asList;
    private TimeUnit asRadio;
    private TimeUnit asCheckbox;
    private TimeUnit asTable;
    private TimeUnit[] asMultiselectList;
    private TimeUnit[] asMultiselectPalette;
    private TimeUnit[] asMultiselectCheckbox;
    private TimeUnit[] asMultiselectTable;

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

    public TimeUnit getAsTable() {
        return asTable;
    }

    public void setAsTable(final TimeUnit asTable) {
        this.asTable = asTable;
    }

    public TimeUnit getAsRadio() {
        return asRadio;
    }

    public void setAsRadio(final TimeUnit asRadio) {
        this.asRadio = asRadio;
    }

    public TimeUnit getAsCheckbox() {
        return asCheckbox;
    }

    public void setAsCheckbox(final TimeUnit asCheckbox) {
        this.asCheckbox = asCheckbox;
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

    public TimeUnit[] getAsMultiselectTable() {
        return asMultiselectTable;
    }

    public void setAsMultiselectTable(final TimeUnit[] asTable) {
        this.asMultiselectTable = asTable;
    }

    public TimeUnit[] getAsMultiselectCheckbox() {
        return asMultiselectCheckbox;
    }

    public void setAsMultiselectCheckbox(final TimeUnit[] asMultiselectCheckbox) {
        this.asMultiselectCheckbox = asMultiselectCheckbox;
    }

}
