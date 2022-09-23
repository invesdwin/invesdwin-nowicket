package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;

@NotThreadSafe
public class JavaScriptToBucketResponseDecorator implements IHeaderResponseDecorator {
    private final String bucketName;

    public JavaScriptToBucketResponseDecorator(final String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public IHeaderResponse decorate(final IHeaderResponse response) {
        return new JavaScriptFilteredIntoFooterHeaderResponse(response, bucketName);
    }
}