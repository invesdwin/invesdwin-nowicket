// CHECKSTYLE:OFF
// @NotThreadSafe
package org.glyptodon.guacamole.servlet;

import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpSession;

/**
 * Provides abstract access to the tunnels associated with a Guacamole session.
 *
 * @author Michael Jumper
 */
@Deprecated
public class JakartaGuacamoleSession {

    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(JakartaGuacamoleSession.class);

    /**
     * Creates a new GuacamoleSession. In prior versions of Guacamole, the GuacamoleSession object stored the tunnels
     * associated with a particular user's use of the HTTP tunnel. The HTTP tunnel now stores all of these tunnels
     * itself, and thus this class is no longer necessary. Its use will result in a warning being logged, and its
     * functions will have no effect.
     *
     * @param session
     *            The HttpSession that older versions of Guacamole would use as tunnel storage. This parameter is now
     *            ignored, and the GuacamoleSession class overall is deprecated.
     */
    public JakartaGuacamoleSession(final HttpSession session) {
        logger.warn("GuacamoleSession is deprecated. It is no longer " + "necessary and its use will have no effect.");
    }

    /**
     * Attaches the given tunnel to this GuacamoleSession. The GuacamoleSession class is now deprecated, and this
     * function has no effect.
     *
     * @param tunnel
     *            The tunnel to attach to this GucacamoleSession.
     */
    public void attachTunnel(final GuacamoleTunnel tunnel) {
        // Deprecated - no effect
    }

    /**
     * Detaches the given tunnel to this GuacamoleSession. The GuacamoleSession class is now deprecated, and this
     * function has no effect.
     *
     * @param tunnel
     *            The tunnel to detach to this GucacamoleSession.
     */
    public void detachTunnel(final GuacamoleTunnel tunnel) {
        // Deprecated - no effect
    }

    /**
     * Returns the tunnel with the given UUID attached to this GuacamoleSession, if any. The GuacamoleSession class is
     * now deprecated, and this function has no effect. It will ALWAYS return null.
     *
     * @param tunnelUUID
     *            The UUID of an attached tunnel.
     *
     * @return The tunnel corresponding to the given UUID, if attached, or null if if no such tunnel is attached.
     */
    public GuacamoleTunnel getTunnel(final String tunnelUUID) {

        // Deprecated - no effect
        return null;

    }

}
