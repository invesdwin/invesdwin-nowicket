// CHECKSTYLE:OFF
package com.bsgcoach.reports.cor.bankloans.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.bankloans.BankLoans;
import com.bsgcoach.util.Err;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum BankLoansSubReportParserRow {
    //    New Bank Loans This Year-----------         5-Year
    NewBankLoansThisYear5Year("New Bank Loans This Year-----------", null, "5-Year") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.getNewBankLoansThisYear().setYear5(value);
        }
    },
    //    10-Year
    NewBankLoansThisYear10Year("New Bank Loans This Year-----------", null, "10-Year") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.getNewBankLoansThisYear().setYear10(value);
        }
    },
    //      Total
    NewBankLoansThisYearTotal("New Bank Loans This Year-----------", null, "Total") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.getNewBankLoansThisYear().setTotal(value);
        }
    },
    //
    //Place-holder
    PlaceHolder("New Bank Loans This Year-----------", "Place-holder", null) {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPlaceHolder(value);
        }
    },
    //Year Initiated-------------------------------           1
    YearInitiated1("Year Initiated-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(1, value);
        }
    },
    //    2
    YearInitiated2("Year Initiated-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(2, value);
        }
    },
    //    3
    YearInitiated3("Year Initiated-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(3, value);
        }
    },
    //    4
    YearInitiated4("Year Initiated-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(4, value);
        }
    },
    //    5
    YearInitiated5("Year Initiated-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(5, value);
        }
    },
    //    6
    YearInitiated6("Year Initiated-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(6, value);
        }
    },
    //    7
    YearInitiated7("Year Initiated-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(7, value);
        }
    },
    //    8
    YearInitiated8("Year Initiated-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(8, value);
        }
    },
    //    9
    YearInitiated9("Year Initiated-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(9, value);
        }
    },
    //    10
    YearInitiated10("Year Initiated-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(10, value);
        }
    },
    //    11
    YearInitiated11("Year Initiated-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(11, value);
        }
    },
    //    12
    YearInitiated12("Year Initiated-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(12, value);
        }
    },
    //    13
    YearInitiated13("Year Initiated-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(13, value);
        }
    },
    //    14
    YearInitiated14("Year Initiated-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(14, value);
        }
    },
    //    15
    YearInitiated15("Year Initiated-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(15, value);
        }
    },
    //    16
    YearInitiated16("Year Initiated-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(16, value);
        }
    },
    //    17
    YearInitiated17("Year Initiated-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(17, value);
        }
    },
    //    18
    YearInitiated18("Year Initiated-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(18, value);
        }
    },
    //    19
    YearInitiated19("Year Initiated-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(19, value);
        }
    },
    //    20
    YearInitiated20("Year Initiated-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(20, value);
        }
    },
    //    21
    YearInitiated21("Year Initiated-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(21, value);
        }
    },
    //    22
    YearInitiated22("Year Initiated-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setYearInitiated(22, value);
        }
    },
    //
    //Original Principal-------------------------------           1
    OriginalPrincipal1("Original Principal-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(1, value);
        }
    },
    //    2
    OriginalPrincipal2("Original Principal-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(2, value);
        }
    },
    //    3
    OriginalPrincipal3("Original Principal-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(3, value);
        }
    },
    //    4
    OriginalPrincipal4("Original Principal-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(4, value);
        }
    },
    //    5
    OriginalPrincipal5("Original Principal-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(5, value);
        }
    },
    //    6
    OriginalPrincipal6("Original Principal-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(6, value);
        }
    },
    //    7
    OriginalPrincipal7("Original Principal-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(7, value);
        }
    },
    //    8
    OriginalPrincipal8("Original Principal-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(8, value);
        }
    },
    //    9
    OriginalPrincipal9("Original Principal-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(9, value);
        }
    },
    //    10
    OriginalPrincipal10("Original Principal-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(10, value);
        }
    },
    //    11
    OriginalPrincipal11("Original Principal-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(11, value);
        }
    },
    //    12
    OriginalPrincipal12("Original Principal-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(12, value);
        }
    },
    //    13
    OriginalPrincipal13("Original Principal-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(13, value);
        }
    },
    //    14
    OriginalPrincipal14("Original Principal-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(14, value);
        }
    },
    //    15
    OriginalPrincipal15("Original Principal-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(15, value);
        }
    },
    //    16
    OriginalPrincipal16("Original Principal-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(16, value);
        }
    },
    //    17
    OriginalPrincipal17("Original Principal-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(17, value);
        }
    },
    //    18
    OriginalPrincipal18("Original Principal-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(18, value);
        }
    },
    //    19
    OriginalPrincipal19("Original Principal-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(19, value);
        }
    },
    //    20
    OriginalPrincipal20("Original Principal-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(20, value);
        }
    },
    //    21
    OriginalPrincipal21("Original Principal-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(21, value);
        }
    },
    //    22
    OriginalPrincipal22("Original Principal-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOriginalPrincipal(22, value);
        }
    },
    //
    //Interest Rate-------------------------------            1
    InterestRate1("Interest Rate-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(1, value);
        }
    },
    //    2
    InterestRate2("Interest Rate-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(2, value);
        }
    },
    //    3
    InterestRate3("Interest Rate-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(3, value);
        }
    },
    //    4
    InterestRate4("Interest Rate-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(4, value);
        }
    },
    //    5
    InterestRate5("Interest Rate-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(5, value);
        }
    },
    //    6
    InterestRate6("Interest Rate-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(6, value);
        }
    },
    //    7
    InterestRate7("Interest Rate-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(7, value);
        }
    },
    //    8
    InterestRate8("Interest Rate-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(8, value);
        }
    },
    //    9
    InterestRate9("Interest Rate-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(9, value);
        }
    },
    //    10
    InterestRate10("Interest Rate-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(10, value);
        }
    },
    //    11
    InterestRate11("Interest Rate-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(11, value);
        }
    },
    //    12
    InterestRate12("Interest Rate-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(12, value);
        }
    },
    //    13
    InterestRate13("Interest Rate-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(13, value);
        }
    },
    //    14
    InterestRate14("Interest Rate-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(14, value);
        }
    },
    //    15
    InterestRate15("Interest Rate-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(15, value);
        }
    },
    //    16
    InterestRate16("Interest Rate-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(16, value);
        }
    },
    //    17
    InterestRate17("Interest Rate-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(17, value);
        }
    },
    //    18
    InterestRate18("Interest Rate-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(18, value);
        }
    },
    //    19
    InterestRate19("Interest Rate-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(19, value);
        }
    },
    //    20
    InterestRate20("Interest Rate-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(20, value);
        }
    },
    //    21
    InterestRate21("Interest Rate-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(21, value);
        }
    },
    //    22
    InterestRate22("Interest Rate-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestRate(22, value);
        }
    },
    //
    //Term-----------------------------------------           1
    Term1("Term-----------------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(1, value);
        }
    },
    //    2
    Term2("Term-----------------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(2, value);
        }
    },
    //    3
    Term3("Term-----------------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(3, value);
        }
    },
    //    4
    Term4("Term-----------------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(4, value);
        }
    },
    //    5
    Term5("Term-----------------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(5, value);
        }
    },
    //    6
    Term6("Term-----------------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(6, value);
        }
    },
    //    7
    Term7("Term-----------------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(7, value);
        }
    },
    //    8
    Term8("Term-----------------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(8, value);
        }
    },
    //    9
    Term9("Term-----------------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(9, value);
        }
    },
    //    10
    Term10("Term-----------------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(10, value);
        }
    },
    //    11
    Term11("Term-----------------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(11, value);
        }
    },
    //    12
    Term12("Term-----------------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(12, value);
        }
    },
    //    13
    Term13("Term-----------------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(13, value);
        }
    },
    //    14
    Term14("Term-----------------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(14, value);
        }
    },
    //    15
    Term15("Term-----------------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(15, value);
        }
    },
    //    16
    Term16("Term-----------------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(16, value);
        }
    },
    //    17
    Term17("Term-----------------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(17, value);
        }
    },
    //    18
    Term18("Term-----------------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(18, value);
        }
    },
    //    19
    Term19("Term-----------------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(19, value);
        }
    },
    //    20
    Term20("Term-----------------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(20, value);
        }
    },
    //    21
    Term21("Term-----------------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(21, value);
        }
    },
    //    22
    Term22("Term-----------------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setTerm(22, value);
        }
    },
    //
    //Outstanding Principal-------------------------------            1
    OutstandingPrincipal1("Outstanding Principal-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(1, value);
        }
    },
    //    2
    OutstandingPrincipal2("Outstanding Principal-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(2, value);
        }
    },
    //    3
    OutstandingPrincipal3("Outstanding Principal-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(3, value);
        }
    },
    //    4
    OutstandingPrincipal4("Outstanding Principal-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(4, value);
        }
    },
    //    5
    OutstandingPrincipal5("Outstanding Principal-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(5, value);
        }
    },
    //    6
    OutstandingPrincipal6("Outstanding Principal-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(6, value);
        }
    },
    //    7
    OutstandingPrincipal7("Outstanding Principal-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(7, value);
        }
    },
    //    8
    OutstandingPrincipal8("Outstanding Principal-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(8, value);
        }
    },
    //    9
    OutstandingPrincipal9("Outstanding Principal-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(9, value);
        }
    },
    //    10
    OutstandingPrincipal10("Outstanding Principal-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(10, value);
        }
    },
    //    11
    OutstandingPrincipal11("Outstanding Principal-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(11, value);
        }
    },
    //    12
    OutstandingPrincipal12("Outstanding Principal-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(12, value);
        }
    },
    //    13
    OutstandingPrincipal13("Outstanding Principal-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(13, value);
        }
    },
    //    14
    OutstandingPrincipal14("Outstanding Principal-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(14, value);
        }
    },
    //    15
    OutstandingPrincipal15("Outstanding Principal-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(15, value);
        }
    },
    //    16
    OutstandingPrincipal16("Outstanding Principal-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(16, value);
        }
    },
    //    17
    OutstandingPrincipal17("Outstanding Principal-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(17, value);
        }
    },
    //    18
    OutstandingPrincipal18("Outstanding Principal-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(18, value);
        }
    },
    //    19
    OutstandingPrincipal19("Outstanding Principal-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(19, value);
        }
    },
    //    20
    OutstandingPrincipal20("Outstanding Principal-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(20, value);
        }
    },
    //    21
    OutstandingPrincipal21("Outstanding Principal-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(21, value);
        }
    },
    //    22
    OutstandingPrincipal22("Outstanding Principal-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setOutstandingPrincipal(22, value);
        }
    },
    //
    //Principal Payment Nxt Yr-------------------------------         1
    PrincipalPaymentNxtYr1("Principal Payment Nxt Yr-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(1, value);
        }
    },
    //    2
    PrincipalPaymentNxtYr2("Principal Payment Nxt Yr-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(2, value);
        }
    },
    //    3
    PrincipalPaymentNxtYr3("Principal Payment Nxt Yr-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(3, value);
        }
    },
    //    4
    PrincipalPaymentNxtYr4("Principal Payment Nxt Yr-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(4, value);
        }
    },
    //    5
    PrincipalPaymentNxtYr5("Principal Payment Nxt Yr-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(5, value);
        }
    },
    //    6
    PrincipalPaymentNxtYr6("Principal Payment Nxt Yr-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(6, value);
        }
    },
    //    7
    PrincipalPaymentNxtYr7("Principal Payment Nxt Yr-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(7, value);
        }
    },
    //    8
    PrincipalPaymentNxtYr8("Principal Payment Nxt Yr-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(8, value);
        }
    },
    //    9
    PrincipalPaymentNxtYr9("Principal Payment Nxt Yr-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(9, value);
        }
    },
    //    10
    PrincipalPaymentNxtYr10("Principal Payment Nxt Yr-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(10, value);
        }
    },
    //    11
    PrincipalPaymentNxtYr11("Principal Payment Nxt Yr-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(11, value);
        }
    },
    //    12
    PrincipalPaymentNxtYr12("Principal Payment Nxt Yr-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(12, value);
        }
    },
    //    13
    PrincipalPaymentNxtYr13("Principal Payment Nxt Yr-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(13, value);
        }
    },
    //    14
    PrincipalPaymentNxtYr14("Principal Payment Nxt Yr-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(14, value);
        }
    },
    //    15
    PrincipalPaymentNxtYr15("Principal Payment Nxt Yr-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(15, value);
        }
    },
    //    16
    PrincipalPaymentNxtYr16("Principal Payment Nxt Yr-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(16, value);
        }
    },
    //    17
    PrincipalPaymentNxtYr17("Principal Payment Nxt Yr-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(17, value);
        }
    },
    //    18
    PrincipalPaymentNxtYr18("Principal Payment Nxt Yr-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(18, value);
        }
    },
    //    19
    PrincipalPaymentNxtYr19("Principal Payment Nxt Yr-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(19, value);
        }
    },
    //    20
    PrincipalPaymentNxtYr20("Principal Payment Nxt Yr-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(20, value);
        }
    },
    //    21
    PrincipalPaymentNxtYr21("Principal Payment Nxt Yr-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(21, value);
        }
    },
    //    22
    PrincipalPaymentNxtYr22("Principal Payment Nxt Yr-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setPrincipalPaymentNxtYr(22, value);
        }
    },
    //
    //Interest Payment Next Year-------------------------------           1
    InterestPaymentNextYear1("Interest Payment Next Year-------------------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(1, value);
        }
    },
    //    2
    InterestPaymentNextYear2("Interest Payment Next Year-------------------------------", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(2, value);
        }
    },
    //    3
    InterestPaymentNextYear3("Interest Payment Next Year-------------------------------", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(3, value);
        }
    },
    //    4
    InterestPaymentNextYear4("Interest Payment Next Year-------------------------------", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(4, value);
        }
    },
    //    5
    InterestPaymentNextYear5("Interest Payment Next Year-------------------------------", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(5, value);
        }
    },
    //    6
    InterestPaymentNextYear6("Interest Payment Next Year-------------------------------", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(6, value);
        }
    },
    //    7
    InterestPaymentNextYear7("Interest Payment Next Year-------------------------------", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(7, value);
        }
    },
    //    8
    InterestPaymentNextYear8("Interest Payment Next Year-------------------------------", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(8, value);
        }
    },
    //    9
    InterestPaymentNextYear9("Interest Payment Next Year-------------------------------", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(9, value);
        }
    },
    //    10
    InterestPaymentNextYear10("Interest Payment Next Year-------------------------------", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(10, value);
        }
    },
    //    11
    InterestPaymentNextYear11("Interest Payment Next Year-------------------------------", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(11, value);
        }
    },
    //    12
    InterestPaymentNextYear12("Interest Payment Next Year-------------------------------", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(12, value);
        }
    },
    //    13
    InterestPaymentNextYear13("Interest Payment Next Year-------------------------------", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(13, value);
        }
    },
    //    14
    InterestPaymentNextYear14("Interest Payment Next Year-------------------------------", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(14, value);
        }
    },
    //    15
    InterestPaymentNextYear15("Interest Payment Next Year-------------------------------", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(15, value);
        }
    },
    //    16
    InterestPaymentNextYear16("Interest Payment Next Year-------------------------------", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(16, value);
        }
    },
    //    17
    InterestPaymentNextYear17("Interest Payment Next Year-------------------------------", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(17, value);
        }
    },
    //    18
    InterestPaymentNextYear18("Interest Payment Next Year-------------------------------", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(18, value);
        }
    },
    //    19
    InterestPaymentNextYear19("Interest Payment Next Year-------------------------------", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(19, value);
        }
    },
    //    20
    InterestPaymentNextYear20("Interest Payment Next Year-------------------------------", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(20, value);
        }
    },
    //    21
    InterestPaymentNextYear21("Interest Payment Next Year-------------------------------", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(21, value);
        }
    },
    //    22
    InterestPaymentNextYear22("Interest Payment Next Year-------------------------------", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setInterestPaymentNextYear(22, value);
        }
    },
    //
    //Nxt Yr Principal Repayment-------------------           1
    NxtYrPrincipalRepaymentOn5YearLoans1("Nxt Yr Principal Repayment-------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(1, value);
        }
    },
    //on 5-Year Loans           2
    NxtYrPrincipalRepaymentOn5YearLoans2("on 5-Year Loans", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(2, value);
        }
    },
    //    3
    NxtYrPrincipalRepaymentOn5YearLoans3("on 5-Year Loans", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(3, value);
        }
    },
    //    4
    NxtYrPrincipalRepaymentOn5YearLoans4("on 5-Year Loans", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(4, value);
        }
    },
    //    5
    NxtYrPrincipalRepaymentOn5YearLoans5("on 5-Year Loans", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(5, value);
        }
    },
    //    6
    NxtYrPrincipalRepaymentOn5YearLoans6("on 5-Year Loans", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(6, value);
        }
    },
    //    7
    NxtYrPrincipalRepaymentOn5YearLoans7("on 5-Year Loans", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(7, value);
        }
    },
    //    8
    NxtYrPrincipalRepaymentOn5YearLoans8("on 5-Year Loans", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(8, value);
        }
    },
    //    9
    NxtYrPrincipalRepaymentOn5YearLoans9("on 5-Year Loans", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(9, value);
        }
    },
    //    10
    NxtYrPrincipalRepaymentOn5YearLoans10("on 5-Year Loans", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(10, value);
        }
    },
    //    11
    NxtYrPrincipalRepaymentOn5YearLoans11("on 5-Year Loans", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(11, value);
        }
    },
    //    12
    NxtYrPrincipalRepaymentOn5YearLoans12("on 5-Year Loans", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(12, value);
        }
    },
    //    13
    NxtYrPrincipalRepaymentOn5YearLoans13("on 5-Year Loans", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(13, value);
        }
    },
    //    14
    NxtYrPrincipalRepaymentOn5YearLoans14("on 5-Year Loans", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(14, value);
        }
    },
    //    15
    NxtYrPrincipalRepaymentOn5YearLoans15("on 5-Year Loans", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(15, value);
        }
    },
    //    16
    NxtYrPrincipalRepaymentOn5YearLoans16("on 5-Year Loans", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(16, value);
        }
    },
    //    17
    NxtYrPrincipalRepaymentOn5YearLoans17("on 5-Year Loans", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(17, value);
        }
    },
    //    18
    NxtYrPrincipalRepaymentOn5YearLoans18("on 5-Year Loans", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(18, value);
        }
    },
    //    19
    NxtYrPrincipalRepaymentOn5YearLoans19("on 5-Year Loans", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(19, value);
        }
    },
    //    20
    NxtYrPrincipalRepaymentOn5YearLoans20("on 5-Year Loans", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(20, value);
        }
    },
    //    21
    NxtYrPrincipalRepaymentOn5YearLoans21("on 5-Year Loans", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(21, value);
        }
    },
    //    22
    NxtYrPrincipalRepaymentOn5YearLoans22("on 5-Year Loans", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setNxtYrPrincipalRepaymentOn5YearLoans(22, value);
        }
    },
    //
    //Early Repayment of---------------------         1
    EarlyRepaymentOf5YearLoans1("Early Repayment of---------------------", null, "1") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            if (report.getEarlyRepaymentOf5YearLoans(1) == null) {
                report.setEarlyRepaymentOf5YearLoans(1, value);
            } else {
                report.setEarlyRepaymentOf10YearLoans(1, value);
            }
        }
    },
    //5-Year Loans          2
    EarlyRepaymentOf5YearLoans2("5-Year Loans", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(2, value);
        }
    },
    //    3
    EarlyRepaymentOf5YearLoans3("5-Year Loans", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(3, value);
        }
    },
    //    4
    EarlyRepaymentOf5YearLoans4("5-Year Loans", null, "4") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(4, value);
        }
    },
    //    5
    EarlyRepaymentOf5YearLoans5("5-Year Loans", null, "5") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(5, value);
        }
    },
    //    6
    EarlyRepaymentOf5YearLoans6("5-Year Loans", null, "6") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(6, value);
        }
    },
    //    7
    EarlyRepaymentOf5YearLoans7("5-Year Loans", null, "7") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(7, value);
        }
    },
    //    8
    EarlyRepaymentOf5YearLoans8("5-Year Loans", null, "8") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(8, value);
        }
    },
    //    9
    EarlyRepaymentOf5YearLoans9("5-Year Loans", null, "9") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(9, value);
        }
    },
    //    10
    EarlyRepaymentOf5YearLoans10("5-Year Loans", null, "10") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(10, value);
        }
    },
    //    11
    EarlyRepaymentOf5YearLoans11("5-Year Loans", null, "11") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(11, value);
        }
    },
    //    12
    EarlyRepaymentOf5YearLoans12("5-Year Loans", null, "12") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(12, value);
        }
    },
    //    13
    EarlyRepaymentOf5YearLoans13("5-Year Loans", null, "13") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(13, value);
        }
    },
    //    14
    EarlyRepaymentOf5YearLoans14("5-Year Loans", null, "14") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(14, value);
        }
    },
    //    15
    EarlyRepaymentOf5YearLoans15("5-Year Loans", null, "15") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(15, value);
        }
    },
    //    16
    EarlyRepaymentOf5YearLoans16("5-Year Loans", null, "16") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(16, value);
        }
    },
    //    17
    EarlyRepaymentOf5YearLoans17("5-Year Loans", null, "17") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(17, value);
        }
    },
    //    18
    EarlyRepaymentOf5YearLoans18("5-Year Loans", null, "18") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(18, value);
        }
    },
    //    19
    EarlyRepaymentOf5YearLoans19("5-Year Loans", null, "19") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(19, value);
        }
    },
    //    20
    EarlyRepaymentOf5YearLoans20("5-Year Loans", null, "20") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(20, value);
        }
    },
    //    21
    EarlyRepaymentOf5YearLoans21("5-Year Loans", null, "21") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(21, value);
        }
    },
    //    22
    EarlyRepaymentOf5YearLoans22("5-Year Loans", null, "22") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf5YearLoans(22, value);
        }
    },
    //
    //Early Repayment of---------------------         1
    //10-Year Loans         2
    EarlyRepaymentOf10YearLoans2("10-Year Loans", null, "2") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf10YearLoans(2, value);
        }
    },
    //    3
    EarlyRepaymentOf10YearLoans3("10-Year Loans", null, "3") {
        @Override
        public void parse(final BankLoans report, final Decimal value) {
            report.setEarlyRepaymentOf10YearLoans(3, value);
        }
    };

    private final String category1;
    private final String title1;
    private final String title2;

    private BankLoansSubReportParserRow(final String category1, final String title1, final String title2) {
        this.category1 = category1;
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category1 + "_" + title1 + "_" + title2;
    }

    public static BankLoansSubReportParserRow valueOfTitle(final String category1, final String title1,
            final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(title1) + "_" + Strings.trim(title2);
        for (final BankLoansSubReportParserRow r : BankLoansSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(final BankLoans report, final Decimal value);

}
