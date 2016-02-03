package com.bsgcoach.reports;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * Replaces all inner quotation marks with a single apostrophe
 * 
 * Example: """ -> "'"
 * 
 * @author subes
 * 
 */
@NotThreadSafe
public class CsvRecordSeparatorPolicy extends DefaultRecordSeparatorPolicy implements InitializingBean {

    private String delimiter = DelimitedLineTokenizer.DELIMITER_COMMA;
    private Pattern nestedQuotePattern;

    public void setDelimiter(final String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public boolean isEndOfRecord(final String line) {
        final String masked = maskNestedQuotes(line);
        return super.isEndOfRecord(masked);
    }

    @Override
    public String postProcess(final String record) {
        final String masked = maskNestedQuotes(record);
        return super.postProcess(masked);
    }

    private String maskNestedQuotes(final String line) {
        final StringBuilder sb = new StringBuilder(line);
        boolean replaced;
        do {
            replaced = false;
            final Matcher matcher = nestedQuotePattern.matcher(sb.toString());
            while (matcher.find()) {
                sb.setCharAt(matcher.start() + 1, '\'');
                replaced = true;
            }
        } while (replaced);
        return sb.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final String nichtDelimiterPattern = "[^" + delimiter + "\n]";
        nestedQuotePattern = Pattern.compile(nichtDelimiterPattern + "\"" + nichtDelimiterPattern);
    }

}
