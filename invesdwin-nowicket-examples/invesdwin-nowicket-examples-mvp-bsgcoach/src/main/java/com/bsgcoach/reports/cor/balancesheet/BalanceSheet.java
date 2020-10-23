package com.bsgcoach.reports.cor.balancesheet;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class BalanceSheet extends AValueObject {

    public static class Assets {
        //    Assets--------  Cash On Hand    
        private Decimal cashOnHand;
        //    Interest Rate     
        private Decimal interestRate;
        //  Accounts Receivable     
        private Decimal accountsReceivable;
        //  Footwear Inventories        
        private Decimal footwearInventories;
        //    Total Curetn Assets     
        private Decimal totalCurrentAssets;

        public class FixedAssets {
            //  Fixed---------- Net Plant Investment    
            private Decimal netPlantInvestment;
            //    Assets    Work in Progress   
            private Decimal workInProgress;
            //        Total Fixed Assets   
            private Decimal totalFixedAssets;

            //          
            public Decimal getNetPlantInvestment() {
                return netPlantInvestment;
            }

            public void setNetPlantInvestment(final Decimal netPlantInvestment) {
                this.netPlantInvestment = netPlantInvestment;
            }

            public Decimal getWorkInProgress() {
                return workInProgress;
            }

            public void setWorkInProgress(final Decimal workInProgress) {
                this.workInProgress = workInProgress;
            }

            public Decimal getTotalFixedAssets() {
                return totalFixedAssets;
            }

            public void setTotalFixedAssets(final Decimal totalFixedAssets) {
                this.totalFixedAssets = totalFixedAssets;
            }

        }

        private final FixedAssets fixedAssets = new FixedAssets();

        public Decimal getCashOnHand() {
            return cashOnHand;
        }

        public void setCashOnHand(final Decimal cashOnHand) {
            this.cashOnHand = cashOnHand;
        }

        public Decimal getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(final Decimal interestRate) {
            this.interestRate = interestRate;
        }

        public Decimal getAccountsReceivable() {
            return accountsReceivable;
        }

        public void setAccountsReceivable(final Decimal accountsReceivable) {
            this.accountsReceivable = accountsReceivable;
        }

        public Decimal getFootwearInventories() {
            return footwearInventories;
        }

        public void setFootwearInventories(final Decimal footwearInventories) {
            this.footwearInventories = footwearInventories;
        }

        public Decimal getTotalCurrentAssets() {
            return totalCurrentAssets;
        }

        public void setTotalCurrentAssets(final Decimal totalCurrentAssets) {
            this.totalCurrentAssets = totalCurrentAssets;
        }

        public FixedAssets getFixedAssets() {
            return fixedAssets;
        }

    }

    private final Assets assets = new Assets();

    //Total Assets            
    private Decimal totalAssets;

    //          
    public static class Liabilities {
        //Liabilities------   Accounts Payable        
        private Decimal accountsPayable;
        //  Overdraft Loan Payable      
        private Decimal overdraftLoanPayable;
        //    Interest Rate     
        private Decimal overdraftLoanInterestRate;
        //  1-Year Loan Payable     
        private Decimal loanPayable1Year;
        //    Interest Rate     
        private Decimal loanPayable1YearInterestRate;
        //  Current Portion of Long-Term
        private Decimal currentPortionOfLongTerm;
        //    Total Current Liabilities     
        private Decimal totalCurrentLiabilities;
        //  Long-Term bank Loans        
        private Decimal longTermBankLoans;
        //    Total Liabilities     
        private Decimal totalLiabilities;

        public Decimal getAccountsPayable() {
            return accountsPayable;
        }

        public void setAccountsPayable(final Decimal accountsPayable) {
            this.accountsPayable = accountsPayable;
        }

        public Decimal getOverdraftLoanPayable() {
            return overdraftLoanPayable;
        }

        public void setOverdraftLoanPayable(final Decimal overdraftLoanPayable) {
            this.overdraftLoanPayable = overdraftLoanPayable;
        }

        public Decimal getOverdraftLoanInterestRate() {
            return overdraftLoanInterestRate;
        }

        public void setOverdraftLoanInterestRate(final Decimal overdraftLoanInterestRate) {
            this.overdraftLoanInterestRate = overdraftLoanInterestRate;
        }

        public Decimal getLoanPayable1Year() {
            return loanPayable1Year;
        }

        public void setLoanPayable1Year(final Decimal loanPayable1Year) {
            this.loanPayable1Year = loanPayable1Year;
        }

        public Decimal getLoanPayable1YearInterestRate() {
            return loanPayable1YearInterestRate;
        }

        public void setLoanPayable1YearInterestRate(final Decimal loanPayable1YearInterestRate) {
            this.loanPayable1YearInterestRate = loanPayable1YearInterestRate;
        }

        public Decimal getCurrentPortionOfLongTerm() {
            return currentPortionOfLongTerm;
        }

        public void setCurrentPortionOfLongTerm(final Decimal currentPortionOfLongTerm) {
            this.currentPortionOfLongTerm = currentPortionOfLongTerm;
        }

        public Decimal getTotalCurrentLiabilities() {
            return totalCurrentLiabilities;
        }

        public void setTotalCurrentLiabilities(final Decimal totalCurrentLiabilities) {
            this.totalCurrentLiabilities = totalCurrentLiabilities;
        }

        public Decimal getLongTermBankLoans() {
            return longTermBankLoans;
        }

        public void setLongTermBankLoans(final Decimal longTermBankLoans) {
            this.longTermBankLoans = longTermBankLoans;
        }

        public Decimal getTotalLiabilities() {
            return totalLiabilities;
        }

        public void setTotalLiabilities(final Decimal totalLiabilities) {
            this.totalLiabilities = totalLiabilities;
        }

    }

    private final Liabilities liabilities = new Liabilities();

    //          
    public static class Equity {
        //Equity----------    Common Stock-----------     This Year
        private Decimal commonStockThisYear;
        //          Last Year
        private Decimal commonStockLastYear;
        //  Additional Capital      This Year
        private Decimal additionalCapitalThisYear;
        //          Last Year
        private Decimal additionalCapitalLastYear;
        //  Retained Earnings       This Year
        private Decimal retainedEarningsThisYear;
        //          Last Year
        private Decimal retainedEarningsLastYear;
        //    Total Equity    
        private Decimal totalEquity;

        public Decimal getCommonStockThisYear() {
            return commonStockThisYear;
        }

        public void setCommonStockThisYear(final Decimal commonStockThisYear) {
            this.commonStockThisYear = commonStockThisYear;
        }

        public Decimal getCommonStockLastYear() {
            return commonStockLastYear;
        }

        public void setCommonStockLastYear(final Decimal commonStockLastYear) {
            this.commonStockLastYear = commonStockLastYear;
        }

        public Decimal getAdditionalCapitalThisYear() {
            return additionalCapitalThisYear;
        }

        public void setAdditionalCapitalThisYear(final Decimal additionalCapitalThisYear) {
            this.additionalCapitalThisYear = additionalCapitalThisYear;
        }

        public Decimal getAdditionalCapitalLastYear() {
            return additionalCapitalLastYear;
        }

        public void setAdditionalCapitalLastYear(final Decimal additionalCapitalLastYear) {
            this.additionalCapitalLastYear = additionalCapitalLastYear;
        }

        public Decimal getRetainedEarningsThisYear() {
            return retainedEarningsThisYear;
        }

        public void setRetainedEarningsThisYear(final Decimal retainedEarningsThisYear) {
            this.retainedEarningsThisYear = retainedEarningsThisYear;
        }

        public Decimal getRetainedEarningsLastYear() {
            return retainedEarningsLastYear;
        }

        public void setRetainedEarningsLastYear(final Decimal retainedEarningsLastYear) {
            this.retainedEarningsLastYear = retainedEarningsLastYear;
        }

        public Decimal getTotalEquity() {
            return totalEquity;
        }

        public void setTotalEquity(final Decimal totalEquity) {
            this.totalEquity = totalEquity;
        }

    }

    private final Equity equity = new Equity();

    //          
    //Total Liabilities and Equity     
    private Decimal totalLiabilitiesAndEquity;
    //Return on Average Equity------------------------------                This Year
    private Decimal returnOnAverageEquityThisYear;

    public Decimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(final Decimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Decimal getTotalLiabilitiesAndEquity() {
        return totalLiabilitiesAndEquity;
    }

    public void setTotalLiabilitiesAndEquity(final Decimal totalLiabilitiesAndEquity) {
        this.totalLiabilitiesAndEquity = totalLiabilitiesAndEquity;
    }

    public Assets getAssets() {
        return assets;
    }

    public Liabilities getLiabilities() {
        return liabilities;
    }

    public Equity getEquity() {
        return equity;
    }

    public Decimal getReturnOnAverageEquityThisYear() {
        return returnOnAverageEquityThisYear;
    }

    public void setReturnOnAverageEquityThisYear(final Decimal returnOnAverageEquityThisYear) {
        this.returnOnAverageEquityThisYear = returnOnAverageEquityThisYear;
    }

}
