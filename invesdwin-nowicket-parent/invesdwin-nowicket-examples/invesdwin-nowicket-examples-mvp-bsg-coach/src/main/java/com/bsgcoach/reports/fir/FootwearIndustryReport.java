package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyLetter;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class FootwearIndustryReport extends AValueObject {

    private final CompanyLetter companyLetter;
    private final IncomeStatement incomeStatement = new IncomeStatement();
    private final BalanceSheet balanceSheet = new BalanceSheet();
    private final DividendData dividendData = new DividendData();
    private final SelectedFinancialStats selectedFinancialStats = new SelectedFinancialStats();
    private final CreditRatingData creditRatingData = new CreditRatingData();

    private boolean hasValues;

    public FootwearIndustryReport(final CompanyLetter companyLetter) {
        this.companyLetter = companyLetter;
    }

    public CompanyLetter getCompanyLetter() {
        return companyLetter;
    }

    public void setHasValues() {
        this.hasValues = true;
    }

    public boolean isHasValues() {
        return hasValues;
    }

    public boolean isAvg() {
        return companyLetter == null;
    }

    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    public DividendData getDividendData() {
        return dividendData;
    }

    public SelectedFinancialStats getSelectedFinancialStats() {
        return selectedFinancialStats;
    }

    public CreditRatingData getCreditRatingData() {
        return creditRatingData;
    }

}
