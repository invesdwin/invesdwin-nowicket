package com.granatasoft.remotelist.website.components.login.button.child.dialog.resource;

import java.net.URI;
import java.util.UUID;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpSession;

import org.apache.wicket.request.resource.IResource;
import org.springframework.security.core.userdetails.User;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.Protocol;
import com.granatasoft.remotelist.persistence.Server;

import de.invesdwin.context.integration.IntegrationProperties;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.nowicket.security.spring.SpringSecurityAuthenticationService;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.uri.URIs;

@NotThreadSafe
@GeneratedMarkup
public class LoginDialog extends AValueObject {

    private final Protocol protocol;
    private final Server server;
    private final ApplicationInstance applicationInstance;

    private String userName = "vbuntu";
    private String password = "vbuntu";
    private String ipAddress = "192.168.178.32";
    private String curSessionId;

    public LoginDialog(final ApplicationInstance applicationInstance) {
        this.protocol = null;
        this.server = null;
        this.applicationInstance = applicationInstance;
        createNewSession(RequestCycles.getContainerRequest().getSession());
    }

    public LoginDialog(final Server server, final Protocol protocol) {
        this.protocol = protocol;
        this.server = server;
        this.applicationInstance = null;
        createNewSession(RequestCycles.getContainerRequest().getSession());
    }

    public void createNewSession(final HttpSession session) {
        removeOldSession(session);
        curSessionId = UUID.randomUUID().toString();
        session.setAttribute(curSessionId, this);
    }

    public void removeOldSession(final HttpSession session) {
        session.removeAttribute(curSessionId);
    }

    @Hidden
    public ApplicationInstance getApplicationInstance() {
        return this.applicationInstance;
    }

    public IResource resourceButton() {
        if (!hideResourceButton()) {
            return protocol.getConfig(server).getResource();
        }
        return null;
    }

    public String resourceButtonTitle() {
        if (!hideResourceButton()) {
            return server.getName() + " (" + protocol + ")";
        }
        return null;
    }

    public boolean hideResourceButton() {
        return protocol == null;
    }

    public URI guacButton() {
        if (!hideGuacButton()) {
            final String server = this.server.getIpv4();
            final SpringSecurityAuthenticationService springSecurityAuthenticationService = (SpringSecurityAuthenticationService) Roles
                    .getAuthenticationService();
            final User user = (User) springSecurityAuthenticationService.getAuthentication().getPrincipal();
            return URIs.asUri(IntegrationProperties.WEBSERVER_BIND_URI + "/secure/index.html?id=" + curSessionId);
        }
        return null;
    }

    public boolean hideGuacButton() {
        return protocol == null;
    }

    public URI uriButton() {

        if (!hideUriButton()) {
            return URIs.asUri(applicationInstance.getUrl());
        }

        return null;
    }

    public boolean hideUriButton() {
        return applicationInstance == null;
    }

    public String uriButtonTitle() {
        return "Web Login";
    }

    @Hidden
    public String getUserName() {
        return userName;
    }

    @Hidden
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Hidden
    public String getPassword() {
        return password;
    }

    @Hidden
    public void setPassword(final String password) {
        this.password = password;
    }

    @Hidden
    public String getIpAddress() {
        return ipAddress;
    }

    @Hidden
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @ModalCloser
    public void close() {
        removeOldSession(RequestCycles.getContainerRequest().getSession());
    }
}
