package com.bsgcoach.reports.cor.bankloans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class BankLoans extends AValueObject {

    public static class NewBankLoansThisYear {
        //    New Bank Loans This Year-----------         5-Year
        private Decimal year5;
        //    10-Year
        private Decimal year10;
        //      Total
        private Decimal total;

        public Decimal getYear5() {
            return year5;
        }

        public void setYear5(final Decimal year5) {
            this.year5 = year5;
        }

        public Decimal getYear10() {
            return year10;
        }

        public void setYear10(final Decimal year10) {
            this.year10 = year10;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

    }

    private final NewBankLoansThisYear newBankLoansThisYear = new NewBankLoansThisYear();
    //
    //Place-holder
    private Decimal placeHolder;

    //Year Initiated-------------------------------           1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> yearInitiated = new HashMap<Integer, Decimal>();
    //
    //Original Principal-------------------------------           1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> originalPrincipal = new HashMap<Integer, Decimal>();
    //
    //Interest Rate-------------------------------            1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> interestRate = new HashMap<Integer, Decimal>();
    //
    //Term-----------------------------------------           1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> term = new HashMap<Integer, Decimal>();
    //
    //Outstanding Principal-------------------------------            1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> outstandingPrincipal = new HashMap<Integer, Decimal>();
    //
    //Principal Payment Nxt Yr-------------------------------         1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> principalPaymentNxtYr = new HashMap<Integer, Decimal>();
    //
    //Interest Payment Next Year-------------------------------           1
    //    2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> interestPaymentNextYear = new HashMap<Integer, Decimal>();
    //
    //Nxt Yr Principal Repayment-------------------           1
    //on 5-Year Loans           2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> nxtYrPrincipalRepaymentOn5YearLoans = new HashMap<Integer, Decimal>();
    //
    //Early Repayment of---------------------         1
    //5-Year Loans          2
    //    3
    //    4
    //    5
    //    6
    //    7
    //    8
    //    9
    //    10
    //    11
    //    12
    //    13
    //    14
    //    15
    //    16
    //    17
    //    18
    //    19
    //    20
    //    21
    //    22
    private final Map<Integer, Decimal> earlyRepaymentOf5YearLoans = new HashMap<Integer, Decimal>();
    //
    //Early Repayment of---------------------         1
    //10-Year Loans         2
    //    3
    private final Map<Integer, Decimal> earlyRepaymentOf10YearLoans = new HashMap<Integer, Decimal>();

    public Decimal getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(final Decimal placeHolder) {
        this.placeHolder = placeHolder;
    }

    public NewBankLoansThisYear getNewBankLoansThisYear() {
        return newBankLoansThisYear;
    }

    public Decimal getYearInitiated(final int index) {
        return yearInitiated.get(index);
    }

    public void setYearInitiated(final int index, final Decimal value) {
        Assertions.assertThat(yearInitiated.put(index, value)).isNull();
    }

    public Decimal getOriginalPrincipal(final int index) {
        return originalPrincipal.get(index);
    }

    public void setOriginalPrincipal(final int index, final Decimal value) {
        Assertions.assertThat(originalPrincipal.put(index, value)).isNull();
    }

    public Decimal getInterestRate(final int index) {
        return interestRate.get(index);
    }

    public void setInterestRate(final int index, final Decimal value) {
        Assertions.assertThat(interestRate.put(index, value)).isNull();
    }

    public Decimal getTerm(final int index) {
        return term.get(index);
    }

    public void setTerm(final int index, final Decimal value) {
        term.put(index, value);
    }

    public Decimal getOutstandingPrincipal(final int index) {
        return outstandingPrincipal.get(index);
    }

    public void setOutstandingPrincipal(final int index, final Decimal value) {
        Assertions.assertThat(outstandingPrincipal.put(index, value)).isNull();
    }

    public Decimal getPrincipalPaymentNxtYr(final int index) {
        return principalPaymentNxtYr.get(index);
    }

    public void setPrincipalPaymentNxtYr(final int index, final Decimal value) {
        Assertions.assertThat(principalPaymentNxtYr.put(index, value)).isNull();
    }

    public Decimal getInterestPaymentNextYear(final int index) {
        return interestPaymentNextYear.get(index);
    }

    public void setInterestPaymentNextYear(final int index, final Decimal value) {
        Assertions.assertThat(interestPaymentNextYear.put(index, value)).isNull();
    }

    public Decimal getNxtYrPrincipalRepaymentOn5YearLoans(final int index) {
        return nxtYrPrincipalRepaymentOn5YearLoans.get(index);
    }

    public void setNxtYrPrincipalRepaymentOn5YearLoans(final int index, final Decimal value) {
        Assertions.assertThat(nxtYrPrincipalRepaymentOn5YearLoans.put(index, value)).isNull();
    }

    public Decimal getEarlyRepaymentOf5YearLoans(final int index) {
        return earlyRepaymentOf5YearLoans.get(index);
    }

    public void setEarlyRepaymentOf5YearLoans(final int index, final Decimal value) {
        Assertions.assertThat(earlyRepaymentOf5YearLoans.put(index, value)).isNull();
    }

    /**
     * MaxKey is 3
     */
    public Decimal getEarlyRepaymentOf10YearLoans(final int index) {
        return earlyRepaymentOf10YearLoans.get(index);
    }

    public void setEarlyRepaymentOf10YearLoans(final int index, final Decimal value) {
        Assertions.assertThat(earlyRepaymentOf10YearLoans.put(index, value)).isNull();
    }

}
