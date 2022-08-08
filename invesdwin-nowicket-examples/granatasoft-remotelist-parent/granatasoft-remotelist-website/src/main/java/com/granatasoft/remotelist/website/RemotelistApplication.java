package com.granatasoft.remotelist.website;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.security.authentication.AuthenticationManager;

import com.granatasoft.remotelist.website.pages.auth.SignInPage;
import com.granatasoft.remotelist.website.pages.auth.SignOutPage;
import com.granatasoft.remotelist.website.pages.error.AccessDeniedPage;
import com.granatasoft.remotelist.website.pages.error.InternalErrorPage;
import com.granatasoft.remotelist.website.pages.error.PageExpiredPage;
import com.granatasoft.remotelist.website.pages.error.PageNotFoundPage;
import com.granatasoft.remotelist.website.pages.remotelist.ShowCategoriesPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.security.spring.SpringSecurityAuthenticationService;
import de.invesdwin.util.collections.Arrays;

@ThreadSafe
@Named
public class RemotelistApplication extends WebApplicationConfigSupport {

    @Inject
    @Named("remotelistAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public Class<? extends WebPage> getHomePage() {
        return ShowCategoriesPage.class;
    }

    @Override
    public ResourceReference getFavicon() {
        return new PackageResourceReference(this.getClass(), "favicon.ico");
    }

    @Override
    public IAuthenticationService getAuthenticationService() {
        return new SpringSecurityAuthenticationService(authenticationManager);
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        return SignInPage.class;
    }

    @Override
    public Class<? extends WebPage> getSignOutPage() {
        return SignOutPage.class;
    }

    @Override
    public Class<? extends WebPage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

    @Override
    public Class<? extends WebPage> getInternalErrorPage() {
        return InternalErrorPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageExpiredPage() {
        return PageExpiredPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageNotFoundPage() {
        return PageNotFoundPage.class;
    }

    @Override
    public List<String> getWicketFilterIgnorePaths() {
        return Arrays.asList("secure");
    }
}
