package com.bsgcoach.util;

import java.io.File;
import java.util.List;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class MailSender {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(MailSender.class);

    private MailSender() {}

    public static void sendFeedbackReportMail(final String subject, final String body, final List<File> attachments) {
        LOG.info("%s\n\n%s", subject, body);
    }
}