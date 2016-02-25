package de.invesdwin.nowicket.examples.guide.pages.wicket.ajaxdatatable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class AjaxDataTableRowGenerator implements Serializable {
    private static long nextId = 1;

    private final String[] firstNames = { "Jacob", "Emily", "Michael", "Sarah", "Matthew", "Brianna", "Nicholas",
            "Samantha", "Christopher", "Hailey", "Abner", "Abby", "Joshua", "Douglas", "Jack", "Keith", "Gerald",
            "Samuel", "Willie", "Larry", "Jose", "Timothy", "Sandra", "Kathleen", "Pamela", "Virginia", "Debra",
            "Maria", "Linda" };
    private final String[] lastNames = { "Smiith", "Johnson", "Williams", "Jones", "Brown", "Donahue", "Bailey", "Rose",
            "Allen", "Black", "Davis", "Clark", "Hall", "Lee", "Baker", "Gonzalez", "Nelson", "Moore", "Wilson",
            "Graham", "Fisher", "Cruz", "Ortiz", "Gomez", "Murray" };

    private synchronized long generateId() {
        return nextId++;
    }

    public AjaxDataTableRow generate() {
        final AjaxDataTableRow contact = new AjaxDataTableRow(generateId(), randomString(firstNames),
                randomString(lastNames), generatePhoneNumber(), generatePhoneNumber(), generateDate());

        return contact;
    }

    public void generate(final Collection<AjaxDataTableRow> collection, final int count) {
        for (int i = 0; i < count; i++) {
            collection.add(generate());
        }
    }

    private String generatePhoneNumber() {
        return new StringBuilder().append(rint(2, 9))
                .append(rint(0, 9))
                .append(rint(0, 9))
                .append("-555-")
                .append(rint(1, 9))
                .append(rint(0, 9))
                .append(rint(0, 9))
                .append(rint(0, 9))
                .toString();
    }

    private int rint(final int min, final int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    private String randomString(final String[] choices) {
        return choices[rint(0, choices.length)];
    }

    private Date generateDate() {
        //CHECKSTYLE:OFF
        final GregorianCalendar gc = new GregorianCalendar();
        //CHECKSTYLE:ON

        final int year = rint(1950, 1985);

        gc.set(Calendar.YEAR, year);

        final int dayOfYear = rint(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

}
