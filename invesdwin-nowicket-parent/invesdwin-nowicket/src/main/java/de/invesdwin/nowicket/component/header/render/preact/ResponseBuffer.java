package de.invesdwin.nowicket.component.header.render.preact;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.string.AppendingStringBuffer;

import jakarta.servlet.http.Cookie;

/**
 * Extracted from: org.apache.wicket.page.PartialPageUpdate.ResponseBuffer
 */
@NotThreadSafe
public class ResponseBuffer extends WebResponse {
    private final AppendingStringBuffer buffer = new AppendingStringBuffer(256);

    private final WebResponse originalResponse;

    public ResponseBuffer(final WebResponse originalResponse) {
        this.originalResponse = originalResponse;
    }

    @Override
    public String encodeURL(final CharSequence url) {
        return originalResponse.encodeURL(url);
    }

    public CharSequence getContents() {
        return buffer;
    }

    @Override
    public void write(final CharSequence cs) {
        buffer.append(cs);
    }

    @Override
    public void reset() {
        buffer.clear();
    }

    @Override
    public void write(final byte[] array) {
        throw new UnsupportedOperationException("Cannot write binary data.");
    }

    @Override
    public void write(final byte[] array, final int offset, final int length) {
        throw new UnsupportedOperationException("Cannot write binary data.");
    }

    @Override
    public Object getContainerResponse() {
        return originalResponse.getContainerResponse();
    }

    @Override
    public void addCookie(final Cookie cookie) {
        originalResponse.addCookie(cookie);
    }

    @Override
    public void clearCookie(final Cookie cookie) {
        originalResponse.clearCookie(cookie);
    }

    @Override
    public boolean isHeaderSupported() {
        return originalResponse.isHeaderSupported();
    }

    @Override
    public void setHeader(final String name, final String value) {
        originalResponse.setHeader(name, value);
    }

    @Override
    public void addHeader(final String name, final String value) {
        originalResponse.addHeader(name, value);
    }

    @Override
    public void setDateHeader(final String name, final java.time.Instant date) {
        originalResponse.setDateHeader(name, date);
    }

    @Override
    public void setContentLength(final long length) {
        originalResponse.setContentLength(length);
    }

    @Override
    public void setContentType(final String mimeType) {
        originalResponse.setContentType(mimeType);
    }

    @Override
    public void setStatus(final int sc) {
        originalResponse.setStatus(sc);
    }

    @Override
    public void sendError(final int sc, final String msg) {
        originalResponse.sendError(sc, msg);
    }

    @Override
    public String encodeRedirectURL(final CharSequence url) {
        return originalResponse.encodeRedirectURL(url);
    }

    @Override
    public void sendRedirect(final String url) {
        originalResponse.sendRedirect(url);
    }

    @Override
    public boolean isRedirect() {
        return originalResponse.isRedirect();
    }

    @Override
    public void flush() {
        originalResponse.flush();
    }
}