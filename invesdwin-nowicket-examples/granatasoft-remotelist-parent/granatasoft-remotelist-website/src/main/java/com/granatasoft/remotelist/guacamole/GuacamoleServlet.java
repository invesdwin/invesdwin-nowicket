package com.granatasoft.remotelist.guacamole;

import java.io.BufferedReader;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.net.GuacamoleSocket;
import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.glyptodon.guacamole.net.InetGuacamoleSocket;
import org.glyptodon.guacamole.net.SimpleGuacamoleTunnel;
import org.glyptodon.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.glyptodon.guacamole.protocol.GuacamoleConfiguration;
import org.glyptodon.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.glyptodon.guacamole.servlet.GuacamoleSession;

import com.granatasoft.remotelist.website.components.login.button.child.dialog.resource.LoginDialog;

import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class GuacamoleServlet extends GuacamoleHTTPTunnelServlet {
    @Override
    protected GuacamoleTunnel doConnect(final HttpServletRequest request) throws GuacamoleException {
        final HttpSession httpSession = request.getSession(true);

        final String sessionId = readBody(request);

        final LoginDialog credentials = (LoginDialog) request.getSession().getAttribute(sessionId);
        credentials.createNewSession(request.getSession());

        // guacd connection information
        final String hostname = "192.168.178.32";
        final String user = credentials.getUserName();
        final String password = credentials.getPassword();
        final int port = 4822;

        // RDP connection information
        final GuacamoleConfiguration config = new GuacamoleConfiguration();
        //        config.setProtocol("rdp");
        //        config.setParameter("security", "any");
        //        config.setParameter("ignore-cert", "true");
        //        config.setParameter("hostname", "192.168.178.24");
        //        config.setParameter("port", "3389");
        //        config.setParameter("username", "Administrator");
        //        config.setParameter("password", "1Nv3sdw1n.");
        config.setProtocol("ssh");
        config.setParameter("hostname", hostname);
        config.setParameter("port", "22");
        config.setParameter("username", user);
        config.setParameter("password", password);

        // Connect to guacd, proxying a connection to the VNC server above
        final GuacamoleSocket socket = new ConfiguredGuacamoleSocket(new InetGuacamoleSocket(hostname, port), config);

        // Create tunnel from now-configured socket
        final GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);

        // Attach tunnel
        final GuacamoleSession session = new GuacamoleSession(httpSession);
        session.attachTunnel(tunnel);

        return tunnel;
    }

    private String readBody(final HttpServletRequest request) {
        String sessionId = null;
        String line = null;
        try {
            final BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                if (line.contains("id")) {
                    sessionId = Strings.removeStart(line, "id:");
                }
            }

        } catch (final Exception e) { /* report an error */
        }

        return sessionId;
    }
}
