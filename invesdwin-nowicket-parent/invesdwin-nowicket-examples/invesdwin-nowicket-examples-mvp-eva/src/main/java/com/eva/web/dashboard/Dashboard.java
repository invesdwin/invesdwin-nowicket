package com.eva.web.dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.eva.web.dashboard.row.DashboardRow;
import com.eva.web.dashboard.row.Pupil;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Dashboard extends AValueObject {

    @NotNull
    private DashboardSorting sorting = DashboardSorting.Standard;

    private final List<DashboardRow> rows = new ArrayList<DashboardRow>();

    public Dashboard() {
        initRows();
    }

    private void initRows() {
        rows.clear();
        final RandomDataGenerator rng = new RandomDataGenerator();
        final List<Pupil> pupils = new ArrayList<Pupil>(Arrays.asList(Pupil.values()));
        while (!pupils.isEmpty()) {
            final int index;
            if (pupils.size() > 1) {
                index = rng.nextInt(0, pupils.size() - 1);
            } else {
                index = 0;
            }
            final Pupil pupil = pupils.remove(index);
            rows.add(new DashboardRow(pupil));
        }
    }

    public DashboardSorting getSorting() {
        return sorting;
    }

    @Eager
    public void setSorting(final DashboardSorting sorting) {
        if (this.sorting != sorting) {
            initRows();
        }
        this.sorting = sorting;
    }

    public List<DashboardRow> getRows() {
        return rows;
    }

}
