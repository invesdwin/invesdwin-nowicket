/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.invesdwin.nowicket.application.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.Request;

/**
 * Basic authenticated web session. Subclasses must provide a method that authenticates the session based on a username
 * and password, and a method implementation that gets the Roles
 * 
 */
@NotThreadSafe
public abstract class AWebSession extends org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     * 
     * @param request
     *            The current request object
     */
    public AWebSession(final Request request) {
        super(request);
    }

    /**
     * @return Current authenticated web session
     */
    public static AWebSession get() {
        return (AWebSession) org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession.get();
    }

    /**
     * Try to logon the user. It'll call {@link #authenticate(String, String)} to do the real work and that is what you
     * need to subclass to provide your own authentication mechanism.
     * 
     * @param username
     * @param password
     * @return true, if logon was successful
     */
    public final boolean signIn(final String username, final String password) {
        final boolean signedIn = authenticate(username, password);
        if (signedIn) {
            final IAuthenticationService authenticationService = Roles.getAuthenticationService();
            if (authenticationService != null && authenticationService.shouldReplaceSessionAfterSignIn()) {
                replaceSession();
            }
        }
        return signedIn;
    }

    @Override
    public abstract Roles getRoles();

    /**
     * Actual authentication check, has to be implemented by subclasses.
     * 
     * @param username
     *            The username
     * @param password
     *            The password
     * @return True if the user was authenticated successfully
     */
    protected abstract boolean authenticate(String username, String password);

    /**
     * Sign the user out.
     */
    protected abstract void signOut();

    /**
     * Call signOut() and remove the logon data from where ever they have been persisted (e.g. Cookies)
     */
    @Override
    public void invalidate() {
        signOut();
        super.invalidate();
    }

}
