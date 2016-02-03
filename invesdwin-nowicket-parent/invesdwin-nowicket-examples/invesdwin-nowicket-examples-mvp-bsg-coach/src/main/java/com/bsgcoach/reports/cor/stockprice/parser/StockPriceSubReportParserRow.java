package com.bsgcoach.reports.cor.stockprice.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.stockprice.StockPrice;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum StockPriceSubReportParserRow {
    //    Current Stock Price
    CurrentStockPrice("Current Stock Price") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setCurrentStockPrice(value);
        }
    },
    //    Beginning Shares Outstanding
    BeginningSharesOutstanding("Beginning Shares Outstanding") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setBeginningSharesOutstanding(value);
        }
    },
    //    Shares Issued
    SharesIssued("Shares Issued") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setSharesIssued(value);
        }
    },
    //    Shares Retired
    SharesRetired("Shares Retired") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setSharesRetired(value);
        }
    },
    //    Ending Shares Outstanding
    EndingSharesOutstanding("Ending Shares Outstanding") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setEndingSharesOutstanding(value);
        }
    },
    //    Stock Issue/Retire Price
    StockIssueRetirePrice("Stock Issue/Retire Price") {
        @Override
        public void parse(final StockPrice report, final Decimal value) {
            report.setStockIssueRetirePrice(value);
        }
    };

    private String category2;

    StockPriceSubReportParserRow(final String category2) {
        this.category2 = category2;
    }

    @Override
    public String toString() {
        return category2;
    }

    public static StockPriceSubReportParserRow valueOfTitle(final String category2) {
        final String trimmedTitle = Strings.trim(category2);
        for (final StockPriceSubReportParserRow r : StockPriceSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(final StockPrice report, final Decimal value);

}
