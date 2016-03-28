package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Tables extends AValueObject {

    private final List<TableRow> tableRowsIterable = new ArrayList<TableRow>() {
        {
            add(new TableRow(1));
            add(new TableRow(2));
            add(new TableRow(3));
        }
    };
    private final TableRow[] tableRowsArray = new TableRow[] { new TableRow(1), new TableRow(2), new TableRow(3) };
    private final List<Byte> bytesIterable = new ArrayList<Byte>() {
        {
            add((byte) 1);
            add((byte) 2);
            add((byte) 3);
        }
    };
    private final Byte[] bytesArray = new Byte[] { 1, 2, 3 };

    public Iterable<TableRow> getTableRowsIterable() {
        return tableRowsIterable;
    }

    public TableRow[] getTableRowsArray() {
        return tableRowsArray;
    }

    @SuppressWarnings("rawtypes")
    public Iterable getObjectsIterable() {
        return tableRowsIterable;
    }

    public Object[] getObjectsArray() {
        return tableRowsArray;
    }

    public Iterable<Byte> getBytesIterable() {
        return bytesIterable;
    }

    public Byte[] getBytesArray() {
        return bytesArray;
    }

}
