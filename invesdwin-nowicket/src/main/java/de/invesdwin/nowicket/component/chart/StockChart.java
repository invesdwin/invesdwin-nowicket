package de.invesdwin.nowicket.component.chart;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;

import com.googlecode.wickedcharts.highcharts.jackson.JsonRenderer;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.theme.Theme;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import com.googlecode.wickedcharts.wicket6.highcharts.features.basic.ChartBehavior;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.component.chart.StockOptions.RangeSelector;
import de.invesdwin.nowicket.component.chart.StockOptions.RangeSelector.RangeSelectorButton;
import de.invesdwin.nowicket.component.chart.StockOptions.RangeSelector.RangeSelectorButton.RangeSelectorButtonType;
import de.invesdwin.nowicket.component.chart.header.HighstockWebjarInitializer;
import de.invesdwin.util.lang.Locales;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class StockChart extends Panel {

    private final IModel<Theme> theme;
    private final IModel<StockOptions> options;

    public StockChart(final String id, final IModel<StockOptions> options) {
        this(id, options, null);
    }

    public StockChart(final String id, final IModel<StockOptions> options, final IModel<Theme> theme) {
        super(id);
        this.options = options;
        this.theme = theme;
        HighstockWebjarInitializer.assertInitialized();
        add(new RefreshingView<StockOptions>("panels") {

            @Override
            protected Iterator<IModel<StockOptions>> getItemModels() {
                return Arrays.asList(options).iterator();
            }

            @Override
            protected void populateItem(final Item<StockOptions> item) {

                item.add(new RefreshingStockChart("panel", item.getModel(), theme));
            }
        });
    }

    private static class RefreshingStockChart extends Chart {

        private final IModel<StockOptions> optionsModel;
        private final IModel<Theme> themeModel;

        RefreshingStockChart(final String id, final IModel<StockOptions> options, final IModel<Theme> theme) {
            super(id, null, null);
            this.optionsModel = options;
            this.themeModel = theme;
        }

        private static Theme adjustTheme(final Theme theme) {
            Theme t = Objects.deepClone(theme);
            if (theme == null) {
                t = new Theme();
            }
            if (Locales.isSameLanguage(Locale.GERMAN, AWebSession.get().getLocale())) {
                //don't overriide settings
                if (t.getLang() == null) {
                    final StockLanguageOptions lang = new StockLanguageOptions();
                    //        contextButtonTitle: Chart context menu
                    lang.setContextButtonTitle("Chart Kontext Menü");
                    //        decimalPoint: "."
                    lang.setDecimalPoint(",");
                    //        downloadJPEG: "Download JPEG image"
                    lang.setDownloadJPEG("JPEG Bild herunterladen");
                    //        downloadPDF: "Download PDF document"
                    lang.setDownloadPDF("PDF Dokument herunterladen");
                    //        downloadPNG: "Download PNG image"
                    lang.setDownloadPNG("PNG Bild herunterladen");
                    //        downloadSVG: "Download SVG vector image"
                    lang.setDownloadSVG("SVG Vector Bild herunterladen");
                    //        loading: Loading...
                    lang.setLoading("Laden...");
                    //        months: ['January' 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    lang.setMonths(Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
                            "September", "Oktober", "November", "Dezember"));
                    //        numericSymbols: [ "k" , "M" , "G" , "T" , "P" , "E"]
                    //        printChart: Print chart
                    lang.setPrintChart("Chart drucken");
                    //        rangeSelectorFrom: From
                    lang.setRangeSelectorFrom("Von");
                    //        rangeSelectorTo: To
                    lang.setRangeSelectorTo("Bis");
                    //        rangeSelectorZoom: Zoom
                    lang.setRangeSelectorZoom("Zoom");
                    //        resetZoom: Reset zoom
                    lang.setResetZoom("Zoom zurücksetzen");
                    //        resetZoomTitle: Reset zoom level 1:1
                    lang.setResetZoomTitle("Zoom zurücksetzen auf 1:1");
                    //        shortMonths:
                    lang.setShortMonths(Arrays.asList("Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep",
                            "Okt", "Nov", "Dez"));
                    //        thousandsSep: ,
                    lang.setThousandsSep(".");
                    //        weekdays: ["Sunday", "Monday",
                    lang.setWeekdays(Arrays.asList("Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag",
                            "Samstag"));
                    t.setLang(lang);
                }
            }
            return t;
        }

        private Options adjustOptions(final StockOptions options) {
            StockOptions o = Objects.deepClone(options);
            if (options == null) {
                o = new StockOptions();
            }
            if (Locales.isSameLanguage(Locale.GERMAN, AWebSession.get().getLocale())) {
                RangeSelector rangeSelector = o.getRangeSelector();
                if (rangeSelector == null) {
                    rangeSelector = new RangeSelector();
                }

                //don't override settings
                if (rangeSelector.getButtons() == null) {
                    final List<RangeSelectorButton> buttons = new ArrayList<RangeSelectorButton>();
                    //                    buttons: [{
                    //                        type: 'month',
                    //                        count: 1,
                    //                        text: '1m'
                    buttons.add(
                            new RangeSelectorButton().setType(RangeSelectorButtonType.month).setCount(1).setText("1m"));
                    //                        type: 'month',
                    //                        count: 3,
                    //                        text: '3m'
                    buttons.add(
                            new RangeSelectorButton().setType(RangeSelectorButtonType.month).setCount(3).setText("3m"));
                    //                        type: 'month',
                    //                        count: 6,
                    //                        text: '6m'
                    buttons.add(
                            new RangeSelectorButton().setType(RangeSelectorButtonType.month).setCount(6).setText("6m"));
                    //                        type: 'ytd',
                    //                        text: 'YTD'
                    buttons.add(new RangeSelectorButton().setType(RangeSelectorButtonType.ytd).setText("YTD"));
                    //                        type: 'year',
                    //                        count: 1,
                    //                        text: '1y'
                    buttons.add(
                            new RangeSelectorButton().setType(RangeSelectorButtonType.year).setCount(1).setText("1y"));
                    //                        type: 'all',
                    //                        text: 'All'
                    buttons.add(new RangeSelectorButton().setType(RangeSelectorButtonType.all).setText("Alle"));

                    rangeSelector.setButtons(buttons);
                }

                o.setRangeSelector(rangeSelector);
            }
            return o;
        }

        @Override
        protected void onConfigure() {
            //lazily set options
            StockOptions options = null;
            if (optionsModel != null) {
                options = optionsModel.getObject();
            }
            setOptions(adjustOptions(options));
            Theme theme = null;
            if (themeModel != null) {
                theme = themeModel.getObject();
            }
            setTheme(adjustTheme(theme));
            super.onConfigure();
        }

        /**
         * Hack to make it run a StockChart instead of normal Chart.
         */
        @Override
        protected ChartBehavior createChartBehavior() {
            return new ChartBehavior(this) {
                @Override
                protected void includeChartJavascript(final IHeaderResponse response, final Options options,
                        final JsonRenderer renderer, final String markupId) {
                    final String chartVarname = RefreshingStockChart.this.getJavaScriptVarName();
                    final String optionsVarname = markupId + "Options";
                    response.render(OnDomReadyHeaderItem.forScript(
                            MessageFormat.format("var {0} = {1};window.{2} = new Highcharts.StockChart({0});",
                                    optionsVarname, renderer.toJson(options), chartVarname)));
                }
            };
        }

    }

}
