package de.invesdwin.nowicket.component.chart;

import javax.annotation.concurrent.NotThreadSafe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.ZoomType;

@NotThreadSafe
public class StockChartOptions extends ChartOptions {

    private Boolean panning;
    @JsonInclude(Include.ALWAYS)
    private ZoomType pinchType;

    public StockChartOptions() {
        //panning does not work in mobile browser anyway and is slow in desktop browser
        setPanning(false);
        //fixes mobile browser scrolling
        setPinchType(null);
        setZoomType(null);
    }

    public Boolean getPanning() {
        return panning;
    }

    public StockChartOptions setPanning(final Boolean panning) {
        this.panning = panning;
        return this;
    }

    public ZoomType getPinchType() {
        return pinchType;
    }

    public StockChartOptions setPinchType(final ZoomType pinchType) {
        this.pinchType = pinchType;
        return this;
    }

    @JsonInclude(Include.ALWAYS)
    @Override
    public ZoomType getZoomType() {
        return super.getZoomType();
    }

}
