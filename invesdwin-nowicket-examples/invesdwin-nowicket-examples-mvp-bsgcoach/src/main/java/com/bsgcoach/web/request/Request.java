package com.bsgcoach.web.request;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.springframework.core.io.FileSystemResource;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.creditrating.CreditRating;
import com.bsgcoach.util.MailSender;
import com.bsgcoach.web.feedback.Feedback;
import com.bsgcoach.web.feedback.sample.SampleFeedback;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.error.Throwables;
import de.invesdwin.util.time.fdate.FDate;

@GeneratedMarkup
@NotThreadSafe
public class Request extends AValueObject {

    public static final int MAX_GAME_ENDS_IN_YEAR = 20;
    private static final int FIRST_REPORT_YEAR = 10;
    private static final int MIN_GAME_ENDS_IN_YEAR = FIRST_REPORT_YEAR + 1;

    private CompanyLetter companyLetter = CompanyLetter.A;
    private Integer gameEndsInYear = MAX_GAME_ENDS_IN_YEAR;
    private CreditRating creditRating;
    private File firReportCsv;
    private File cirReportCsv;
    private File corReportCsv;

    @NotNull
    public CompanyLetter getCompanyLetter() {
        return companyLetter;
    }

    public void setCompanyLetter(final CompanyLetter companyLetter) {
        this.companyLetter = companyLetter;
    }

    public List<Integer> getGameEndsInYearChoice() {
        final List<Integer> values = new ArrayList<Integer>();
        for (int i = MIN_GAME_ENDS_IN_YEAR; i <= MAX_GAME_ENDS_IN_YEAR; i++) {
            values.add(i);
        }
        return values;
    }

    @NotNull
    public Integer getGameEndsInYear() {
        return gameEndsInYear;
    }

    public void setGameEndsInYear(final Integer gameEndsInYear) {
        this.gameEndsInYear = gameEndsInYear;
    }

    @NotNull
    public CreditRating getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(final CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public void uploadFirReportCsv(final File firReportCsv) {
        this.firReportCsv = firReportCsv;
    }

    public String getUploadedFirReportCsv() {
        if (firReportCsv != null) {
            return firReportCsv.getName();
        } else {
            return null;
        }
    }

    public void uploadCirReportCsv(final File cirReportCsv) {
        this.cirReportCsv = cirReportCsv;
    }

    public String getUploadedCirReportCsv() {
        if (cirReportCsv != null) {
            return cirReportCsv.getName();
        } else {
            return null;
        }
    }

    public void uploadCorReportCsv(final File corReportCsv) {
        this.corReportCsv = corReportCsv;
    }

    public String getUploadedCorReportCsv() {
        if (corReportCsv != null) {
            return corReportCsv.getName();
        } else {
            return null;
        }
    }

    public Feedback generateFeedback() throws Exception {
        final List<String> missingReportCsvs = new ArrayList<String>();
        if (firReportCsv == null) {
            missingReportCsvs.add("FIR");
        }
        if (cirReportCsv == null) {
            missingReportCsvs.add("CIR");
        }
        if (corReportCsv == null) {
            missingReportCsvs.add("COR");
        }
        if (!missingReportCsvs.isEmpty()) {
            final StringBuilder message = new StringBuilder(
                    "Please upload the following missing report csv files:<ul>");
            for (final String csv : missingReportCsvs) {
                message.append("<li>");
                message.append(csv);
                message.append("</li>");
            }
            message.append("</ul>");
            message.append("Then try again to get your feedback.");
            throw new Exception(message.toString());
        }

        try {
            final Reports reports = new Reports(companyLetter, gameEndsInYear, creditRating,
                    new FileSystemResource(firReportCsv), new FileSystemResource(corReportCsv),
                    new FileSystemResource(cirReportCsv));
            sendMail("Feedback given on https://github.com/invesdwin/invesdwin-nowicket", null,
                    Collections.emptyList());
            return new Feedback(reports);
        } catch (final Throwable t) {
            final String message = "Our feedback tool was not able to read the reports you have provided. Please try again and make sure that you upload the correct ones.<br><br>If this does not help, then please <a href=\"mailto:gsubes@gmail.com\">email us</a>. It would be of great help to us if you shortly describe the problem and attach the reports you have used as well as a screenshot of the error message. We will get the feedback tool back on track as soon as possible.<br><br><h4>Reason:</h4> "
                    + t.getMessage();
            final Exception exception = new Exception(message, t);
            sendMail("Error in getting feedback on https://github.com/invesdwin/invesdwin-nowicket", exception,
                    Arrays.asList(firReportCsv, cirReportCsv, corReportCsv));
            throw exception;
        }
    }

    protected void sendMail(final String subject, final Throwable exception, final List<File> attachments) {
        final ClientInfo clientInfo = AWebSession.get().getClientInfo();
        final ABaseWebApplication application = ABaseWebApplication.get();
        new Thread() {
            @Override
            public void run() {
                super.run();
                String body = "date=" + new FDate() + "\n";
                body += "productionEnvironment=" + application.usesDeploymentConfig() + "\n";
                if (clientInfo instanceof WebClientInfo) {
                    final WebClientInfo webClientInfo = (WebClientInfo) clientInfo;
                    body += "userAgent=" + webClientInfo.getUserAgent();
                    body += webClientInfo.getProperties().toString();
                } else {
                    body += "no client info!";
                }
                body += "\n\n";
                body += Request.this.toStringMultiline();
                if (exception != null) {
                    body += "\n\n";
                    body += Throwables.getFullStackTrace(exception);
                }
                MailSender.sendFeedbackReportMail(subject, body, attachments);
            }
        }.start();
    }

    public String exportHowtoImg() {
        return "exportHowto.png";
    }

    @Forced
    public SampleFeedback generateSampleFeedback() throws Exception {
        return new SampleFeedback();
    }

    public String title() {
        return "Request.title";
    }

    public void upload() {
    }

}
