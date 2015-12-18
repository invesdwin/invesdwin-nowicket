package de.invesdwin.nowicket.component.chart;

import javax.annotation.concurrent.NotThreadSafe;

import com.googlecode.wickedcharts.highcharts.options.LanguageOptions;

@NotThreadSafe
public class StockLanguageOptions extends LanguageOptions {

    private String contextButtonTitle;
    private String rangeSelectorZoom;
    private String rangeSelectorTo;
    private String rangeSelectorFrom;
    private String printChart;

    public StockLanguageOptions setContextButtonTitle(final String contextButtonTitle) {
        this.contextButtonTitle = contextButtonTitle;
        return this;
    }

    public String getContextButtonTitle() {
        return contextButtonTitle;
    }

    public StockLanguageOptions setRangeSelectorZoom(final String rangeSelectorZoom) {
        this.rangeSelectorZoom = rangeSelectorZoom;
        return this;
    }

    public String getRangeSelectorZoom() {
        return rangeSelectorZoom;
    }

    public StockLanguageOptions setRangeSelectorTo(final String rangeSelectorTo) {
        this.rangeSelectorTo = rangeSelectorTo;
        return this;
    }

    public String getRangeSelectorTo() {
        return rangeSelectorTo;
    }

    public StockLanguageOptions setRangeSelectorFrom(final String rangeSelectorFrom) {
        this.rangeSelectorFrom = rangeSelectorFrom;
        return this;
    }

    public String getRangeSelectorFrom() {
        return rangeSelectorFrom;
    }

    public StockLanguageOptions setPrintChart(final String printChart) {
        this.printChart = printChart;
        return this;
    }

    public String getPrintChart() {
        return printChart;
    }

}
