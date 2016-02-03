package com.bsgcoach.reports.cor.incomestatement;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class IncomeStatement extends AValueObject {

    public class PerRegion {

        private final CompanyRegion companyRegion;

        public class GrossRevenues {
            //N.A.------------    Gross Revenues----------        Internet
            private Decimal internet;
            //    Wholesale
            private Decimal wholesale;
            //    P-Label
            private Decimal pLabel;
            //      Total
            private Decimal total;

            public Decimal getInternet() {
                return internet;
            }

            public void setInternet(final Decimal internet) {
                this.internet = internet;
            }

            public Decimal getWholesale() {
                return wholesale;
            }

            public void setWholesale(final Decimal wholesale) {
                this.wholesale = wholesale;
            }

            public Decimal getPLabel() {
                return pLabel;
            }

            public void setPLabel(final Decimal pLabel) {
                this.pLabel = pLabel;
            }

            public Decimal getTotal() {
                return total;
            }

            public void setTotal(final Decimal total) {
                this.total = total;
            }

        }

        private final GrossRevenues grossRevenues = new GrossRevenues();

        //Exchange Rate Adjustment
        private Decimal exchangeRateAdjustment;
        //Net Revenues
        private Decimal netRevenues;

        public class OperatingCosts {

            //Operating Costs----------       COPS
            private Decimal cops;
            //    Whse
            private Decimal whse;
            //    Mktng
            private Decimal mktng;
            //    Admin
            private Decimal admin;

            public Decimal getCops() {
                return cops;
            }

            public void setCops(final Decimal cops) {
                this.cops = cops;
            }

            public Decimal getWhse() {
                return whse;
            }

            public void setWhse(final Decimal whse) {
                this.whse = whse;
            }

            public Decimal getMktng() {
                return mktng;
            }

            public void setMktng(final Decimal mktng) {
                this.mktng = mktng;
            }

            public Decimal getAdmin() {
                return admin;
            }

            public void setAdmin(final Decimal admin) {
                this.admin = admin;
            }

        }

        private final OperatingCosts operatingCosts = new OperatingCosts();

        //Operating Profit
        private Decimal operatingProfit;

        public PerRegion(final CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public Decimal getExchangeRateAdjustment() {
            return exchangeRateAdjustment;
        }

        public void setExchangeRateAdjustment(final Decimal exchangeRateAdjustment) {
            this.exchangeRateAdjustment = exchangeRateAdjustment;
        }

        public Decimal getNetRevenues() {
            return netRevenues;
        }

        public void setNetRevenues(final Decimal netRevenues) {
            this.netRevenues = netRevenues;
        }

        public Decimal getOperatingProfit() {
            return operatingProfit;
        }

        public void setOperatingProfit(final Decimal operatingProfit) {
            this.operatingProfit = operatingProfit;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

        public GrossRevenues getGrossRevenues() {
            return grossRevenues;
        }

        public OperatingCosts getOperatingCosts() {
            return operatingCosts;
        }

    }

    private final ALoadingCache<CompanyRegion, PerRegion> perRegion = new ALoadingCache<CompanyRegion, PerRegion>() {
        @Override
        protected PerRegion loadValue(final CompanyRegion key) {
            return new PerRegion(key);
        }
    };

    //Overall-------- Gross Revenues----------        Internet
    //    Wholesale
    //    P-Label
    //      Total
    //Exchange Rate Adjustment
    //Net Revenues
    //Operating Costs----------       COPS
    //    Whse
    //    Mktng
    //    Admin
    //Operating Profit
    private final PerRegion overall = new PerRegion(null);

    //Interest Income (Expenses)
    private Decimal interestIncomeExpenses;
    //Refunds (Fines)
    private Decimal refundsFines;
    //Pre-Tax Profit (Loss)
    private Decimal preTaxProfitLoss;
    //Taxable Income
    private Decimal taxableIncome;
    //Income Taxes
    private Decimal incomeTaxes;
    //Net Profit (Loss)
    private Decimal netProfitLoss;
    //
    //This Yr-------  EPS
    private Decimal epsThisYr;
    //    DPS
    private Decimal dpsThisYr;
    //Last Yr-------  EPS
    private Decimal epsLastYr;
    //    DPS
    private Decimal dpsLastYr;
    //
    //Losses Not Yet Recovered------------            Current Yr
    private Decimal lossesNotYetRecoveredCurrentYr;
    //    Year -1
    private Decimal lossesNotYetRecoveredYearMinus1;
    //    Year -2
    private Decimal lossesNotYetRecoveredYearMinus2;

    public Decimal getInterestIncomeExpenses() {
        return interestIncomeExpenses;
    }

    public void setInterestIncomeExpenses(final Decimal interestIncomeExpenses) {
        this.interestIncomeExpenses = interestIncomeExpenses;
    }

    public Decimal getRefundsFines() {
        return refundsFines;
    }

    public void setRefundsFines(final Decimal refundsFines) {
        this.refundsFines = refundsFines;
    }

    public Decimal getPreTaxProfitLoss() {
        return preTaxProfitLoss;
    }

    public void setPreTaxProfitLoss(final Decimal preTaxProfitLoss) {
        this.preTaxProfitLoss = preTaxProfitLoss;
    }

    public Decimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(final Decimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public Decimal getIncomeTaxes() {
        return incomeTaxes;
    }

    public void setIncomeTaxes(final Decimal incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }

    public Decimal getNetProfitLoss() {
        return netProfitLoss;
    }

    public void setNetProfitLoss(final Decimal netProfitLoss) {
        this.netProfitLoss = netProfitLoss;
    }

    public Decimal getEpsThisYr() {
        return epsThisYr;
    }

    public void setEpsThisYr(final Decimal epsThisYr) {
        this.epsThisYr = epsThisYr;
    }

    public Decimal getDpsThisYr() {
        return dpsThisYr;
    }

    public void setDpsThisYr(final Decimal dpsThisYr) {
        this.dpsThisYr = dpsThisYr;
    }

    public Decimal getEpsLastYr() {
        return epsLastYr;
    }

    public void setEpsLastYr(final Decimal epsLastYr) {
        this.epsLastYr = epsLastYr;
    }

    public Decimal getDpsLastYr() {
        return dpsLastYr;
    }

    public void setDpsLastYr(final Decimal dpsLastYr) {
        this.dpsLastYr = dpsLastYr;
    }

    public Decimal getLossesNotYetRecoveredCurrentYr() {
        return lossesNotYetRecoveredCurrentYr;
    }

    public void setLossesNotYetRecoveredCurrentYr(final Decimal lossesNotYetRecoveredCurrentYr) {
        this.lossesNotYetRecoveredCurrentYr = lossesNotYetRecoveredCurrentYr;
    }

    public Decimal getLossesNotYetRecoveredYearMinus1() {
        return lossesNotYetRecoveredYearMinus1;
    }

    public void setLossesNotYetRecoveredYearMinus1(final Decimal lossesNotYetRecoveredYearMinus1) {
        this.lossesNotYetRecoveredYearMinus1 = lossesNotYetRecoveredYearMinus1;
    }

    public Decimal getLossesNotYetRecoveredYearMinus2() {
        return lossesNotYetRecoveredYearMinus2;
    }

    public void setLossesNotYetRecoveredYearMinus2(final Decimal lossesNotYetRecoveredYearMinus2) {
        this.lossesNotYetRecoveredYearMinus2 = lossesNotYetRecoveredYearMinus2;
    }

    public PerRegion getPerRegion(final CompanyRegion companyRegion) {
        return perRegion.get(companyRegion);
    }

    public PerRegion getOverall() {
        return overall;
    }

}
