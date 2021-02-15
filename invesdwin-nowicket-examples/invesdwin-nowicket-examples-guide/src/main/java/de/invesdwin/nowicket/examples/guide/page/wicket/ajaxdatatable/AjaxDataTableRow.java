package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable;

import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.ColumnOrder;
import de.invesdwin.norva.beanpath.spi.element.table.column.TableRemoveFromButtonColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.table.column.TableSelectionButtonColumnBeanPathElement;
import de.invesdwin.util.bean.AValueObject;

/**
 * BornDate gets hidden, since it is not in ColumnOrder, could also have added a hide utility element or
 * 
 * You can also reference the parents remove from utility element.
 *
 */
@ColumnOrder({ TableSelectionButtonColumnBeanPathElement.COLUMN_ID, AjaxDataTableRowConstants.id,
        AjaxDataTableRowConstants.firstName, AjaxDataTableRowConstants.lastName, AjaxDataTableRowConstants.homePhone,
        AjaxDataTableRowConstants.cellPhone, TableRemoveFromButtonColumnBeanPathElement.COLUMN_ID })
@NotThreadSafe
public class AjaxDataTableRow extends AValueObject {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String homePhone;
    private final String cellPhone;
    private final Date bornDate;

    public AjaxDataTableRow(final long id, final String firstName, final String lastName, final String homePhone,
            final String cellPhone, final Date bornDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhone = homePhone;
        this.cellPhone = cellPhone;
        this.bornDate = bornDate;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    //hidden since not in @ColumnOrder
    public Date getBornDate() {
        return bornDate;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

}
