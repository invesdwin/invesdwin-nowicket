package de.invesdwin.nowicket.component.chart;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import com.googlecode.wickedcharts.highcharts.options.PlotOptions;

@NotThreadSafe
public class StockPlotOptions extends PlotOptions {

    private DataGrouping dataGrouping;

    public StockPlotOptions setDataGrouping(final DataGrouping dataGrouping) {
        this.dataGrouping = dataGrouping;
        return this;
    }

    public DataGrouping getDataGrouping() {
        return dataGrouping;
    }

    public static class DataGrouping implements Serializable {

        private Approximation approximation;
        private Boolean enabled;
        private Boolean forced;

        public enum Approximation {
            average,
            sum,
            range,
            ohlc,
            open,
            high,
            low,
            close;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public DataGrouping setEnabled(final Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Boolean getForced() {
            return forced;
        }

        public DataGrouping setForced(final Boolean forced) {
            this.forced = forced;
            return this;
        }

        public DataGrouping setApproximation(final Approximation approximation) {
            this.approximation = approximation;
            return this;
        }

        public Approximation getApproximation() {
            return approximation;
        }

    }

}
