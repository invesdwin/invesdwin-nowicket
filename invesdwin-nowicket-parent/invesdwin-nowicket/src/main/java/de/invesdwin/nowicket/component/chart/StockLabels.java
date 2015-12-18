package de.invesdwin.nowicket.component.chart;

import javax.annotation.concurrent.NotThreadSafe;

import com.googlecode.wickedcharts.highcharts.options.Labels;

@NotThreadSafe
public class StockLabels extends Labels {

    private String format;

    public String getFormat() {
        return format;
    }

    public StockLabels setFormat(final String format) {
        this.format = format;
        return this;
    }

}
