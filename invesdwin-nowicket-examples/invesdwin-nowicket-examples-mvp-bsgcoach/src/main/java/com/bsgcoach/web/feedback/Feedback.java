package com.bsgcoach.web.feedback;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.rank.Rank;
import com.bsgcoach.rules.IRule;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
@Configurable
public class Feedback extends AValueObject {

    @Inject
    private transient IRule[] rules;
    private final transient Reports reports;
    private final List<RuleMatch> ruleMatches;

    public Feedback(final Reports reports) {
        this.reports = reports;
        this.ruleMatches = new ArrayList<RuleMatch>();
    }

    @PostConstruct
    private void matchRules() {
        if (reports != null) {
            for (final IRule rule : rules) {
                if (rule.getSignificance(reports) != null) {
                    ruleMatches.add(new RuleMatch(reports, rule));
                }
            }
            RuleMatch.COMPARATOR.asDescending().asNotNullSafe().sort(ruleMatches);
            while (ruleMatches.size() > 5) {
                ruleMatches.remove(ruleMatches.size() - 1);
            }
            for (int i = 0; i < ruleMatches.size(); i++) {
                ruleMatches.get(i).setRank(Rank.valueOfIndex(i));
            }
        }
    }

    public List<RuleMatch> getRuleMatches() {
        return ruleMatches;
    }

    public String title() {
        return "Feedback.title";
    }

}
