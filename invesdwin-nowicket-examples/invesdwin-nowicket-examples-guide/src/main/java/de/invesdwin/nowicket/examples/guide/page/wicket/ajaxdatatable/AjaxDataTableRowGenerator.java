package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.math.random.IRandomGenerator;
import de.invesdwin.util.math.random.PseudoRandomGenerator;

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
    private final IRandomGenerator random = new PseudoRandomGenerator();

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
        return new StringBuilder().append(random.nextInt(2, 9))
                .append(random.nextInt(0, 9))
                .append(random.nextInt(0, 9))
                .append("-555-")
                .append(random.nextInt(1, 9))
                .append(random.nextInt(0, 9))
                .append(random.nextInt(0, 9))
                .append(random.nextInt(0, 9))
                .toString();
    }

    private String randomString(final String[] choices) {
        return choices[random.nextInt(0, choices.length)];
    }

    private Date generateDate() {
        //CHECKSTYLE:OFF
        final GregorianCalendar gc = new GregorianCalendar();
        //CHECKSTYLE:ON

        final int year = random.nextInt(1950, 1985);

        gc.set(Calendar.YEAR, year);

        final int dayOfYear = random.nextInt(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

}
