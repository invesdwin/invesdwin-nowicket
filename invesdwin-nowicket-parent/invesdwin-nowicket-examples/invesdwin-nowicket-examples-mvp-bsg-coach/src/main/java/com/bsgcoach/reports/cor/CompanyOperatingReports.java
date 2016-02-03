package com.bsgcoach.reports.cor;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.reports.cor.adminexpenses.AdminExpenses;
import com.bsgcoach.reports.cor.balancesheet.BalanceSheet;
import com.bsgcoach.reports.cor.bankloans.BankLoans;
import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions;
import com.bsgcoach.reports.cor.cashflow.CashFlow;
import com.bsgcoach.reports.cor.celebrityendorsements.CelebrityEndorsements;
import com.bsgcoach.reports.cor.incomestatement.IncomeStatement;
import com.bsgcoach.reports.cor.internetmarketingexpenses.InternetMarketingExpenses;
import com.bsgcoach.reports.cor.internetmarketperformance.InternetMarketPerformance;
import com.bsgcoach.reports.cor.plannedshippingpercentages.PlannedShippingPercentages;
import com.bsgcoach.reports.cor.plantoperations.PlantOperations;
import com.bsgcoach.reports.cor.privatelabeloffersandbids.PrivateLabelOffersAndBids;
import com.bsgcoach.reports.cor.privatelabeloperations.PrivateLabelOperations;
import com.bsgcoach.reports.cor.stockprice.StockPrice;
import com.bsgcoach.reports.cor.wholesalemarketingexpenses.WholesaleMarketingExpenses;
import com.bsgcoach.reports.cor.whslemarketperformance.WhsleMarketPerformance;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;

@NotThreadSafe
public class CompanyOperatingReports extends AValueObject {

    private final ALoadingCache<CompanyRegion, PlantOperations> plantOperations = new ALoadingCache<CompanyRegion, PlantOperations>() {
        @Override
        protected PlantOperations loadValue(final CompanyRegion key) {
            return new PlantOperations(key);
        }
    };
    private final PlannedShippingPercentages plannedShippingPercentages = new PlannedShippingPercentages();
    private final ALoadingCache<CompanyRegion, BrandedWarehouseProductions> brandedWarehouseProductions = new ALoadingCache<CompanyRegion, BrandedWarehouseProductions>() {
        @Override
        protected BrandedWarehouseProductions loadValue(final CompanyRegion key) {
            return new BrandedWarehouseProductions(key);
        }
    };
    private final CelebrityEndorsements celebrityEndorsements = new CelebrityEndorsements();
    private final InternetMarketingExpenses internetMarketingExpenses = new InternetMarketingExpenses();
    private final ALoadingCache<CompanyRegion, WholesaleMarketingExpenses> wholesaleMarketingExpenses = new ALoadingCache<CompanyRegion, WholesaleMarketingExpenses>() {
        @Override
        protected WholesaleMarketingExpenses loadValue(final CompanyRegion key) {
            return new WholesaleMarketingExpenses(key);
        }
    };
    private final AdminExpenses adminExpenses = new AdminExpenses();
    private final ALoadingCache<CompanyRegion, PrivateLabelOffersAndBids> privateLabelOffersAndBids = new ALoadingCache<CompanyRegion, PrivateLabelOffersAndBids>() {
        @Override
        protected PrivateLabelOffersAndBids loadValue(final CompanyRegion key) {
            return new PrivateLabelOffersAndBids(key);
        }
    };
    private final ALoadingCache<CompanyRegion, PrivateLabelOperations> privateLabelOperations = new ALoadingCache<CompanyRegion, PrivateLabelOperations>() {
        @Override
        protected PrivateLabelOperations loadValue(final CompanyRegion key) {
            return new PrivateLabelOperations(key);
        }
    };
    private final ALoadingCache<CompanyRegion, InternetMarketPerformance> internetMarketPerformance = new ALoadingCache<CompanyRegion, InternetMarketPerformance>() {
        @Override
        protected InternetMarketPerformance loadValue(final CompanyRegion key) {
            return new InternetMarketPerformance(key);
        }
    };
    private final ALoadingCache<CompanyRegion, WhsleMarketPerformance> whsleMarketPerformance = new ALoadingCache<CompanyRegion, WhsleMarketPerformance>() {
        @Override
        protected WhsleMarketPerformance loadValue(final CompanyRegion key) {
            return new WhsleMarketPerformance(key);
        }
    };
    private final IncomeStatement incomeStatement = new IncomeStatement();
    private final CashFlow cashFlow = new CashFlow();
    private final BalanceSheet balanceSheet = new BalanceSheet();
    private final StockPrice stockPrice = new StockPrice();
    private final BankLoans bankLoans = new BankLoans();

    public PlantOperations getPlantOperations(final CompanyRegion companyRegion) {
        return plantOperations.get(companyRegion);
    }

    public PlannedShippingPercentages getPlannedShippingPercentages() {
        return plannedShippingPercentages;
    }

    public BrandedWarehouseProductions getBrandedWarehouseProductions(final CompanyRegion companyRegion) {
        return brandedWarehouseProductions.get(companyRegion);
    }

    public PrivateLabelOperations getPrivateLabelOperations(final CompanyRegion companyRegion) {
        return privateLabelOperations.get(companyRegion);
    }

    public CelebrityEndorsements getCelebrityEndorsements() {
        return celebrityEndorsements;
    }

    public InternetMarketingExpenses getInternetMarketingExpenses() {
        return internetMarketingExpenses;
    }

    public WholesaleMarketingExpenses getWholesaleMarketingExpenses(final CompanyRegion companyRegion) {
        return wholesaleMarketingExpenses.get(companyRegion);
    }

    public AdminExpenses getAdminExpenses() {
        return adminExpenses;
    }

    public PrivateLabelOffersAndBids getPrivateLabelOffersAndBids(final CompanyRegion companyRegion) {
        return privateLabelOffersAndBids.get(companyRegion);
    }

    public InternetMarketPerformance getInternetMarketPerformance(final CompanyRegion companyRegion) {
        return internetMarketPerformance.get(companyRegion);
    }

    public WhsleMarketPerformance getWhsleMarketPerformance(final CompanyRegion companyRegion) {
        return whsleMarketPerformance.get(companyRegion);
    }

    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }

    public CashFlow getCashFlow() {
        return cashFlow;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    public StockPrice getStockPrice() {
        return stockPrice;
    }

    public BankLoans getBankLoans() {
        return bankLoans;
    }

}
