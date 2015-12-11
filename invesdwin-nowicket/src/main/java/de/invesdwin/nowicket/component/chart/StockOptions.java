package de.invesdwin.nowicket.component.chart;

import java.io.Serializable;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import com.googlecode.wickedcharts.highcharts.options.CreditOptions;
import com.googlecode.wickedcharts.highcharts.options.ExportingOptions;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.series.Series;

@NotThreadSafe
public class StockOptions extends Options {

    private Navigator navigator;
    private RangeSelector rangeSelector;
    private Scrollbar scrollbar;

    public StockOptions() {
        setCredits(new CreditOptions().setEnabled(false));
        setTooltip(new StockTooltip());
        setLegend(new Legend().setEnabled(true));
        setExporting(new ExportingOptions().setEnabled(false));
        setScrollbar(new Scrollbar());
        //http://stackoverflow.com/questions/14330793/how-to-completely-disable-the-mouse-finger-interaction-for-highcharts
        setChart(new StockChartOptions());
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public StockOptions setNavigator(final Navigator navigator) {
        this.navigator = navigator;
        return this;
    }

    public RangeSelector getRangeSelector() {
        return rangeSelector;
    }

    public StockOptions setRangeSelector(final RangeSelector rangeSelector) {
        this.rangeSelector = rangeSelector;
        return this;
    }

    public Scrollbar getScrollbar() {
        return scrollbar;
    }

    public StockOptions setScrollbar(final Scrollbar scrollbar) {
        this.scrollbar = scrollbar;
        return this;
    }

    public static class Navigator implements Serializable {

        private Integer baseSeries;
        private Series series;

        public Integer getBaseSeries() {
            return baseSeries;
        }

        public Navigator setBaseSeries(final Integer baseSeries) {
            this.baseSeries = baseSeries;
            return this;
        }

        public Series getSeries() {
            return series;
        }

        public Navigator setSeries(final Series series) {
            this.series = series;
            return this;
        }

    }

    public static class RangeSelector implements Serializable {

        private List<RangeSelectorButton> buttons;

        public List<RangeSelectorButton> getButtons() {
            return buttons;
        }

        public RangeSelector setButtons(final List<RangeSelectorButton> buttons) {
            this.buttons = buttons;
            return this;
        }

        public static class RangeSelectorButton implements Serializable {

            private RangeSelectorButtonType type;
            private Integer count;
            private String text;

            public RangeSelectorButtonType getType() {
                return type;
            }

            public RangeSelectorButton setType(final RangeSelectorButtonType type) {
                this.type = type;
                return this;
            }

            public Integer getCount() {
                return count;
            }

            public RangeSelectorButton setCount(final Integer count) {
                this.count = count;
                return this;
            }

            public String getText() {
                return text;
            }

            public RangeSelectorButton setText(final String text) {
                this.text = text;
                return this;
            }

            public enum RangeSelectorButtonType {
                month,
                ytd,
                year,
                all;
            }
        }

    }

    public static class Scrollbar implements Serializable {

        private Boolean enabled;

        public Scrollbar() {
            setEnabled(false);
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public Scrollbar setEnabled(final Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

    }

}
