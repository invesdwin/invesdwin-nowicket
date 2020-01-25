package com.bsgcoach.rules;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;

public interface IRule {

    String getFeedbackText(Reports reports);

    CorrespondingTheories getCorrespondingTheories();

    Significance getSignificance(Reports reports);

}
