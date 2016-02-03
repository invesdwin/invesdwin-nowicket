package com.bsgcoach.reports.cor.cashflow;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class CashFlow extends AValueObject {

    //    Beginning Cash Balance      
    private Decimal beginningCashBalance;

    //    
    public class CashInflows {
        //    Cash Inflows----------------        Receipts from Sales 
        private Decimal receiptsFromSales;
        //            Bank----------  1-Year
        private Decimal bankLoans1Year;
        //              Loans 5-Year
        private Decimal bankLoans5Year;
        //                10-Year
        private Decimal bankLoands10Year;
        //            Stock Issue 
        private Decimal stockIssue;
        //            Sale of Existing Plant
        private Decimal saleOfExistingPlant;
        //            Overdraft Loan  
        private Decimal overdraftLoan;
        //            Interest on Cash Bal
        private Decimal interestOnCashBal;
        //            Refund  
        private Decimal refund;

        public Decimal getReceiptsFromSales() {
            return receiptsFromSales;
        }

        public void setReceiptsFromSales(final Decimal receiptsFromSales) {
            this.receiptsFromSales = receiptsFromSales;
        }

        public Decimal getBankLoans1Year() {
            return bankLoans1Year;
        }

        public void setBankLoans1Year(final Decimal bankLoans1Year) {
            this.bankLoans1Year = bankLoans1Year;
        }

        public Decimal getBankLoans5Year() {
            return bankLoans5Year;
        }

        public void setBankLoans5Year(final Decimal bankLoans5Year) {
            this.bankLoans5Year = bankLoans5Year;
        }

        public Decimal getBankLoands10Year() {
            return bankLoands10Year;
        }

        public void setBankLoands10Year(final Decimal bankLoands10Year) {
            this.bankLoands10Year = bankLoands10Year;
        }

        public Decimal getStockIssue() {
            return stockIssue;
        }

        public void setStockIssue(final Decimal stockIssue) {
            this.stockIssue = stockIssue;
        }

        public Decimal getSaleOfExistingPlant() {
            return saleOfExistingPlant;
        }

        public void setSaleOfExistingPlant(final Decimal saleOfExistingPlant) {
            this.saleOfExistingPlant = saleOfExistingPlant;
        }

        public Decimal getOverdraftLoan() {
            return overdraftLoan;
        }

        public void setOverdraftLoan(final Decimal overdraftLoan) {
            this.overdraftLoan = overdraftLoan;
        }

        public Decimal getInterestOnCashBal() {
            return interestOnCashBal;
        }

        public void setInterestOnCashBal(final Decimal interestOnCashBal) {
            this.interestOnCashBal = interestOnCashBal;
        }

        public Decimal getRefund() {
            return refund;
        }

        public void setRefund(final Decimal refund) {
            this.refund = refund;
        }

    }

    private final CashInflows cashInflows = new CashInflows();

    //                
    //    Total Cash Available from All Sources   
    private Decimal totalCashAvailableFromAllSources;

    //                
    public class CashOutlays {

        //    Cash----------  Payments to Materials Suppliers   
        private Decimal paymentsToMaterialsSuppliers;
        //      Outlays   Production Expenses     
        private Decimal productionExpenses;
        //        Distribution and Warehouse
        private Decimal distributionAndWarehouse;
        //        Marketing and Administrative   
        private Decimal marketingAndAdministrative;
        //        Capital-------  Capacity Purchase
        private Decimal capitalCapacityPurchase;
        //            Plant Upgrades  
        private Decimal capitalPlantUpgrades;
        //            Capacity Construct  
        private Decimal capitalCapacityConstruct;

        public class PrincipalRepay {
            //        Principal-----------    Overdraft Loan  
            private Decimal overdraftLoan;
            //          Repay 1-Year Loan 
            private Decimal loan1Year;
            //            5-Year Loans    
            private Decimal loans5Year;
            //            10-Year Loans   
            private Decimal loans10Year;

            public Decimal getOverdraftLoan() {
                return overdraftLoan;
            }

            public void setOverdraftLoan(final Decimal overdraftLoan) {
                this.overdraftLoan = overdraftLoan;
            }

            public Decimal getLoan1Year() {
                return loan1Year;
            }

            public void setLoan1Year(final Decimal loan1Year) {
                this.loan1Year = loan1Year;
            }

            public Decimal getLoans5Year() {
                return loans5Year;
            }

            public void setLoans5Year(final Decimal loans5Year) {
                this.loans5Year = loans5Year;
            }

            public Decimal getLoans10Year() {
                return loans10Year;
            }

            public void setLoans10Year(final Decimal loans10Year) {
                this.loans10Year = loans10Year;
            }

        }

        private final PrincipalRepay principalRepay = new PrincipalRepay();

        public class InterestPmts {
            //        Interest--------    Overdraft Loan  
            private Decimal overdraftLoan;
            //          Pmts  Bank Loans  
            private Decimal bankLoans;

            public Decimal getOverdraftLoan() {
                return overdraftLoan;
            }

            public void setOverdraftLoan(final Decimal overdraftLoan) {
                this.overdraftLoan = overdraftLoan;
            }

            public Decimal getBankLoans() {
                return bankLoans;
            }

            public void setBankLoans(final Decimal bankLoans) {
                this.bankLoans = bankLoans;
            }

        }

        private final InterestPmts interestPmts = new InterestPmts();

        //        Stock Repurchase        
        private Decimal stockRepurchase;
        //        Income Tax Payments   
        private Decimal incomeTaxPayments;
        //        Dividend Payments       
        private Decimal dividendPayments;
        //        Fine        
        private Decimal fine;
        //        Charitable Contributions      
        private Decimal charitableContributions;

        public Decimal getPaymentsToMaterialsSuppliers() {
            return paymentsToMaterialsSuppliers;
        }

        public void setPaymentsToMaterialsSuppliers(final Decimal paymentsToMaterialsSuppliers) {
            this.paymentsToMaterialsSuppliers = paymentsToMaterialsSuppliers;
        }

        public Decimal getProductionExpenses() {
            return productionExpenses;
        }

        public void setProductionExpenses(final Decimal productionExpenses) {
            this.productionExpenses = productionExpenses;
        }

        public Decimal getDistributionAndWarehouse() {
            return distributionAndWarehouse;
        }

        public void setDistributionAndWarehouse(final Decimal distributionAndWarehouse) {
            this.distributionAndWarehouse = distributionAndWarehouse;
        }

        public Decimal getMarketingAndAdministrative() {
            return marketingAndAdministrative;
        }

        public void setMarketingAndAdministrative(final Decimal marketingAndAdministrative) {
            this.marketingAndAdministrative = marketingAndAdministrative;
        }

        public Decimal getCapitalCapacityPurchase() {
            return capitalCapacityPurchase;
        }

        public void setCapitalCapacityPurchase(final Decimal capitalCapacityPurchase) {
            this.capitalCapacityPurchase = capitalCapacityPurchase;
        }

        public Decimal getCapitalPlantUpgrades() {
            return capitalPlantUpgrades;
        }

        public void setCapitalPlantUpgrades(final Decimal capitalPlantUpgrades) {
            this.capitalPlantUpgrades = capitalPlantUpgrades;
        }

        public Decimal getCapitalCapacityConstruct() {
            return capitalCapacityConstruct;
        }

        public void setCapitalCapacityConstruct(final Decimal capitalCapacityConstruct) {
            this.capitalCapacityConstruct = capitalCapacityConstruct;
        }

        public Decimal getStockRepurchase() {
            return stockRepurchase;
        }

        public void setStockRepurchase(final Decimal stockRepurchase) {
            this.stockRepurchase = stockRepurchase;
        }

        public Decimal getIncomeTaxPayments() {
            return incomeTaxPayments;
        }

        public void setIncomeTaxPayments(final Decimal incomeTaxPayments) {
            this.incomeTaxPayments = incomeTaxPayments;
        }

        public Decimal getDividendPayments() {
            return dividendPayments;
        }

        public void setDividendPayments(final Decimal dividendPayments) {
            this.dividendPayments = dividendPayments;
        }

        public Decimal getFine() {
            return fine;
        }

        public void setFine(final Decimal fine) {
            this.fine = fine;
        }

        public Decimal getCharitableContributions() {
            return charitableContributions;
        }

        public void setCharitableContributions(final Decimal charitableContributions) {
            this.charitableContributions = charitableContributions;
        }

        public PrincipalRepay getPrincipalRepay() {
            return principalRepay;
        }

        public InterestPmts getInterestPmts() {
            return interestPmts;
        }

    }

    private final CashOutlays cashOutlays = new CashOutlays();

    //    Total Cash Outlays        
    private Decimal totalCashOutlays;
    //                
    //    Net Cash Balance           
    private Decimal netCashBalance;

    public Decimal getBeginningCashBalance() {
        return beginningCashBalance;
    }

    public void setBeginningCashBalance(final Decimal beginningCashBalance) {
        this.beginningCashBalance = beginningCashBalance;
    }

    public Decimal getTotalCashAvailableFromAllSources() {
        return totalCashAvailableFromAllSources;
    }

    public void setTotalCashAvailableFromAllSources(final Decimal totalCashAvailableFromAllSources) {
        this.totalCashAvailableFromAllSources = totalCashAvailableFromAllSources;
    }

    public Decimal getTotalCashOutlays() {
        return totalCashOutlays;
    }

    public void setTotalCashOutlays(final Decimal totalCashOutlays) {
        this.totalCashOutlays = totalCashOutlays;
    }

    public Decimal getNetCashBalance() {
        return netCashBalance;
    }

    public void setNetCashBalance(final Decimal netCashBalance) {
        this.netCashBalance = netCashBalance;
    }

    public CashInflows getCashInflows() {
        return cashInflows;
    }

    public CashOutlays getCashOutlays() {
        return cashOutlays;
    }

}
