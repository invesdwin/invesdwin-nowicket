package com.bsgcoach.rules.debtequityratio;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.creditrating.CreditRating;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;

import jakarta.inject.Named;

@Named
@Immutable
public class DebtEquityRatioGood implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can take loans to finance their assets and investments. Using debt for financing offers companies the opportunity to improve equity-based performance measures like ROE and EPS. This opportunity is called leverage which primarily depends on the level interest payments. The better the credit rating, the lower the interest rates for loans, and the greater the leverage opportunity. Your company has a credit rating of "
                + FeedbackFormatter.highlightVariable(reports.getCreditRating().toString())
                + " resulting in a relatively low interest rate for bank loans. You should consider the leverage effect from using debt as a source for financing.";
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.DebtEquityRatio;
    }

    @Override
    public Significance getSignificance(final Reports reports) {
        if (reports.getCreditRating() == CreditRating.APlus) {
            return Significance._10;
        }
        if (reports.getCreditRating() == CreditRating.A) {
            return Significance._9;
        }
        if (reports.getCreditRating() == CreditRating.AMinus) {
            return Significance._8;
        }
        return null;
    }

}
