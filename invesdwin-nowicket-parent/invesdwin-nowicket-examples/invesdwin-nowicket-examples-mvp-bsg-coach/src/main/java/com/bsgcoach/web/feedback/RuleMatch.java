package com.bsgcoach.web.feedback;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.ResourceReference;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.rank.Rank;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.IRule;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.ADelegateComparator;

@NotThreadSafe
@GeneratedMarkup
public class RuleMatch extends AValueObject {

    public static final ADelegateComparator<RuleMatch> COMPARATOR = new ADelegateComparator<RuleMatch>() {
        @Override
        protected Comparable<?> getCompareCriteria(final RuleMatch e) {
            return e;
        }
    };

    private Rank rawRank;
    private final String ruleName;
    private final String feedbackText;
    private final Significance rawSignificance;
    private final CorrespondingTheories correspondingTheories;

    public RuleMatch(final Reports reports, final IRule rule) {
        this.ruleName = rule.getCorrespondingTheories().toString();
        this.feedbackText = rule.getFeedbackText(reports);
        this.rawSignificance = rule.getSignificance(reports);
        this.correspondingTheories = rule.getCorrespondingTheories();
    }

    public ResourceReference rank() {
        return rawRank.getIcon();
    }

    public void setRank(final Rank rank) {
        this.rawRank = rank;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public ResourceReference significance() {
        return rawSignificance.getIcon();
    }

    public String getSignificancePercent() {
        return rawSignificance.getPercent();
    }

    public String readMore() {
        if (correspondingTheories == null) {
            return null;
        } else {
            return correspondingTheories.getReadMoreUrl();
        }
    }

    public boolean hideReadMore() {
        return readMore() == null;
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof RuleMatch) {
            final RuleMatch cO = (RuleMatch) o;
            return ((Integer) rawSignificance.ordinal()).compareTo(cO.rawSignificance.ordinal());
        } else {
            return 1;
        }
    }

}
