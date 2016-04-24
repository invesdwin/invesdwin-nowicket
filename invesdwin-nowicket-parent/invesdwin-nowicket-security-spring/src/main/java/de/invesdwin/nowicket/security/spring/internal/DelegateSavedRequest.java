package de.invesdwin.nowicket.security.spring.internal;

import javax.annotation.concurrent.Immutable;

import org.springframework.security.web.savedrequest.SavedRequest;

import de.invesdwin.nowicket.application.auth.ISavedRequest;

@Immutable
public class DelegateSavedRequest implements ISavedRequest {

    private final SavedRequest delegate;

    public DelegateSavedRequest(final SavedRequest delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getRedirectUrl() {
        return delegate.getRedirectUrl();
    }

    public SavedRequest getDelegate() {
        return delegate;
    }

}
