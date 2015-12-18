package de.invesdwin.nowicket.component.chart;

import javax.annotation.concurrent.NotThreadSafe;

import com.googlecode.wickedcharts.highcharts.options.Tooltip;

@NotThreadSafe
public class StockTooltip extends Tooltip {

    private Boolean followPointer;
    private Boolean followTouchMove;

    public StockTooltip() {
        //http://stackoverflow.com/questions/14330793/how-to-completely-disable-the-mouse-finger-interaction-for-highcharts
        setFollowPointer(false);
        setFollowTouchMove(false);
        setShared(true);
        setValueDecimals(2);
    }

    public Boolean getFollowPointer() {
        return followPointer;
    }

    public StockTooltip setFollowPointer(final Boolean followPointer) {
        this.followPointer = followPointer;
        return this;
    }

    public Boolean getFollowTouchMove() {
        return followTouchMove;
    }

    public StockTooltip setFollowTouchMove(final Boolean followTouchMove) {
        this.followTouchMove = followTouchMove;
        return this;
    }

}
