package com.bsgcoach.web.feedback.sample;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.creditrating.CreditRating;
import com.bsgcoach.web.feedback.Feedback;
import com.bsgcoach.web.request.CompanyLetter;
import com.bsgcoach.web.request.Request;

@NotThreadSafe
public class SampleFeedback extends Feedback {

    public SampleFeedback() throws Exception {
        super(createSampleReports());
    }

    private static Reports createSampleReports() throws Exception {
        final String path = "/" + SampleFeedback.class.getPackage().getName().replace(".", "/") + "/";
        final Reports reports = new Reports(CompanyLetter.A, Request.MAX_GAME_ENDS_IN_YEAR, CreditRating.APlus,
                new ClassPathResource(path + "sample_FIR_Y12.csv"), new ClassPathResource(path + "sample_COR_Y12.csv"),
                new ClassPathResource(path + "sample_CIR_Y12.csv"));
        return reports;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null) {
            return getClass().equals(obj.getClass());
        } else {
            return false;
        }
    }

}
