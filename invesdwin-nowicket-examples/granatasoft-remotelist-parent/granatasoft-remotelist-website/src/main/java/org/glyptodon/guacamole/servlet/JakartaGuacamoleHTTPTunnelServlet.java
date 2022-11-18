// CHECKSTYLE:OFF
// @ThreadSafe
package org.glyptodon.guacamole.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.glyptodon.guacamole.GuacamoleClientException;
import org.glyptodon.guacamole.GuacamoleConnectionClosedException;
import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.GuacamoleResourceNotFoundException;
import org.glyptodon.guacamole.GuacamoleServerException;
import org.glyptodon.guacamole.io.GuacamoleReader;
import org.glyptodon.guacamole.io.GuacamoleWriter;
import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.glyptodon.guacamole.protocol.GuacamoleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A HttpServlet implementing and abstracting the operations required by the HTTP implementation of the JavaScript
 * Guacamole client's tunnel.
 *
 * @author Michael Jumper
 */
public abstract class JakartaGuacamoleHTTPTunnelServlet extends HttpServlet {

    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(JakartaGuacamoleHTTPTunnelServlet.class);

    /**
     * Map of absolutely all active tunnels using HTTP, indexed by tunnel UUID.
     */
    private final GuacamoleHTTPTunnelMap tunnels = new GuacamoleHTTPTunnelMap();

    /**
     * The prefix of the query string which denotes a tunnel read operation.
     */
    private static final String READ_PREFIX = "read:";

    /**
     * The prefix of the query string which denotes a tunnel write operation.
     */
    private static final String WRITE_PREFIX = "write:";

    /**
     * The length of the read prefix, in characters.
     */
    private static final int READ_PREFIX_LENGTH = READ_PREFIX.length();

    /**
     * The length of the write prefix, in characters.
     */
    private static final int WRITE_PREFIX_LENGTH = WRITE_PREFIX.length();

    /**
     * The length of every tunnel UUID, in characters.
     */
    private static final int UUID_LENGTH = 36;

    /**
     * Registers the given tunnel such that future read/write requests to that tunnel will be properly directed.
     *
     * @param tunnel
     *            The tunnel to register.
     */
    protected void registerTunnel(final GuacamoleTunnel tunnel) {
        tunnels.put(tunnel.getUUID().toString(), tunnel);
        logger.debug("Registered tunnel \"{}\".", tunnel.getUUID());
    }

    /**
     * Deregisters the given tunnel such that future read/write requests to that tunnel will be rejected.
     *
     * @param tunnel
     *            The tunnel to deregister.
     */
    protected void deregisterTunnel(final GuacamoleTunnel tunnel) {
        tunnels.remove(tunnel.getUUID().toString());
        logger.debug("Deregistered tunnel \"{}\".", tunnel.getUUID());
    }

    /**
     * Returns the tunnel with the given UUID, if it has been registered with registerTunnel() and not yet deregistered
     * with deregisterTunnel().
     *
     * @param tunnelUUID
     *            The UUID of registered tunnel.
     *
     * @return The tunnel corresponding to the given UUID.
     *
     * @throws GuacamoleException
     *             If the requested tunnel does not exist because it has not yet been registered or it has been
     *             deregistered.
     */
    protected GuacamoleTunnel getTunnel(final String tunnelUUID) throws GuacamoleException {

        // Pull tunnel from map
        final GuacamoleTunnel tunnel = tunnels.get(tunnelUUID);
        if (tunnel == null) {
            throw new GuacamoleResourceNotFoundException("No such tunnel.");
        }

        return tunnel;

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
        handleTunnelRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        handleTunnelRequest(request, response);
    }

    /**
     * Sends an error on the given HTTP response using the information within the given GuacamoleStatus.
     *
     * @param response
     *            The HTTP response to use to send the error.
     *
     * @param guacStatus
     *            The status to send
     *
     * @param message
     *            A human-readable message that can be presented to the user.
     *
     * @throws ServletException
     *             If an error prevents sending of the error code.
     */
    protected void sendError(final HttpServletResponse response, final GuacamoleStatus guacStatus, final String message)
            throws ServletException {

        try {

            // If response not committed, send error code and message
            if (!response.isCommitted()) {
                response.addHeader("Guacamole-Status-Code", Integer.toString(guacStatus.getGuacamoleStatusCode()));
                response.addHeader("Guacamole-Error-Message", message);
                response.sendError(guacStatus.getHttpStatusCode());
            }

        } catch (final IOException ioe) {

            // If unable to send error at all due to I/O problems,
            // rethrow as servlet exception
            throw new ServletException(ioe);

        }

    }

    /**
     * Dispatches every HTTP GET and POST request to the appropriate handler function based on the query string.
     *
     * @param request
     *            The HttpServletRequest associated with the GET or POST request received.
     *
     * @param response
     *            The HttpServletResponse associated with the GET or POST request received.
     *
     * @throws ServletException
     *             If an error occurs while servicing the request.
     */
    protected void handleTunnelRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {

        try {

            final String query = request.getQueryString();
            if (query == null) {
                throw new GuacamoleClientException("No query string provided.");
            }

            // If connect operation, call doConnect() and return tunnel UUID
            // in response.
            if (query.equals("connect")) {

                final GuacamoleTunnel tunnel = doConnect(request);
                if (tunnel != null) {

                    // Register newly-created tunnel
                    registerTunnel(tunnel);

                    try {
                        // Ensure buggy browsers do not cache response
                        response.setHeader("Cache-Control", "no-cache");

                        // Send UUID to client
                        response.getWriter().print(tunnel.getUUID().toString());
                    } catch (final IOException e) {
                        throw new GuacamoleServerException(e);
                    }

                } else {
                    throw new GuacamoleResourceNotFoundException("No tunnel created.");
                }

            }

            // If read operation, call doRead() with tunnel UUID, ignoring any
            // characters following the tunnel UUID.
            else if (query.startsWith(READ_PREFIX)) {
                doRead(request, response, query.substring(READ_PREFIX_LENGTH, READ_PREFIX_LENGTH + UUID_LENGTH));
            } else if (query.startsWith(WRITE_PREFIX)) {
                doWrite(request, response, query.substring(WRITE_PREFIX_LENGTH, WRITE_PREFIX_LENGTH + UUID_LENGTH));
            } else {
                throw new GuacamoleClientException("Invalid tunnel operation: " + query);
            }
        }

        // Catch any thrown guacamole exception and attempt to pass within the
        // HTTP response, logging each error appropriately.
        catch (final GuacamoleClientException e) {
            logger.warn("HTTP tunnel request rejected: {}", e.getMessage());
            sendError(response, e.getStatus(), e.getMessage());
        } catch (final GuacamoleException e) {
            logger.error("HTTP tunnel request failed: {}", e.getMessage());
            logger.debug("Internal error in HTTP tunnel.", e);
            sendError(response, e.getStatus(), "Internal server error.");
        }

    }

    /**
     * Called whenever the JavaScript Guacamole client makes a connection request via HTTP. It it up to the implementor
     * of this function to define what conditions must be met for a tunnel to be configured and returned as a result of
     * this connection request (whether some sort of credentials must be specified, for example).
     *
     * @param request
     *            The HttpServletRequest associated with the connection request received. Any parameters specified along
     *            with the connection request can be read from this object.
     *
     * @return A newly constructed GuacamoleTunnel if successful, null otherwise.
     *
     * @throws GuacamoleException
     *             If an error occurs while constructing the GuacamoleTunnel, or if the conditions required for
     *             connection are not met.
     */
    protected abstract GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException;

    /**
     * Called whenever the JavaScript Guacamole client makes a read request. This function should in general not be
     * overridden, as it already contains a proper implementation of the read operation.
     *
     * @param request
     *            The HttpServletRequest associated with the read request received.
     *
     * @param response
     *            The HttpServletResponse associated with the write request received. Any data to be sent to the client
     *            in response to the write request should be written to the response body of this HttpServletResponse.
     *
     * @param tunnelUUID
     *            The UUID of the tunnel to read from, as specified in the write request. This tunnel must have been
     *            created by a previous call to doConnect().
     *
     * @throws GuacamoleException
     *             If an error occurs while handling the read request.
     */
    protected void doRead(final HttpServletRequest request, final HttpServletResponse response, final String tunnelUUID)
            throws GuacamoleException {

        // Get tunnel, ensure tunnel exists
        final GuacamoleTunnel tunnel = getTunnel(tunnelUUID);

        // Ensure tunnel is open
        if (!tunnel.isOpen()) {
            throw new GuacamoleResourceNotFoundException("Tunnel is closed.");
        }

        // Obtain exclusive read access
        final GuacamoleReader reader = tunnel.acquireReader();

        try {

            // Note that although we are sending text, Webkit browsers will
            // buffer 1024 bytes before starting a normal stream if we use
            // anything but application/octet-stream.
            response.setContentType("application/octet-stream");
            response.setHeader("Cache-Control", "no-cache");

            // Get writer for response
            final Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

            // Stream data to response, ensuring output stream is closed
            try {

                // Deregister tunnel and throw error if we reach EOF without
                // having ever sent any data
                char[] message = reader.read();
                if (message == null) {
                    throw new GuacamoleConnectionClosedException("Tunnel reached end of stream.");
                }

                // For all messages, until another stream is ready (we send at least one message)
                do {

                    // Get message output bytes
                    out.write(message, 0, message.length);

                    // Flush if we expect to wait
                    if (!reader.available()) {
                        out.flush();
                        response.flushBuffer();
                    }

                    // No more messages another stream can take over
                    if (tunnel.hasQueuedReaderThreads()) {
                        break;
                    }

                } while (tunnel.isOpen() && (message = reader.read()) != null);

                // Close tunnel immediately upon EOF
                if (message == null) {
                    deregisterTunnel(tunnel);
                    tunnel.close();
                }

                // End-of-instructions marker
                out.write("0.;");
                out.flush();
                response.flushBuffer();
            }

            // Send end-of-stream marker and close tunnel if connection is closed
            catch (final GuacamoleConnectionClosedException e) {

                // Deregister and close
                deregisterTunnel(tunnel);
                tunnel.close();

                // End-of-instructions marker
                out.write("0.;");
                out.flush();
                response.flushBuffer();

            }

            catch (final GuacamoleException e) {

                // Deregister and close
                deregisterTunnel(tunnel);
                tunnel.close();

                throw e;
            }

            // Always close output stream
            finally {
                out.close();
            }

        } catch (final IOException e) {

            // Log typically frequent I/O error if desired
            logger.debug("Error writing to servlet output stream", e);

            // Deregister and close
            deregisterTunnel(tunnel);
            tunnel.close();

        } finally {
            tunnel.releaseReader();
        }

    }

    /**
     * Called whenever the JavaScript Guacamole client makes a write request. This function should in general not be
     * overridden, as it already contains a proper implementation of the write operation.
     *
     * @param request
     *            The HttpServletRequest associated with the write request received. Any data to be written will be
     *            specified within the body of this request.
     *
     * @param response
     *            The HttpServletResponse associated with the write request received.
     *
     * @param tunnelUUID
     *            The UUID of the tunnel to write to, as specified in the write request. This tunnel must have been
     *            created by a previous call to doConnect().
     *
     * @throws GuacamoleException
     *             If an error occurs while handling the write request.
     */
    protected void doWrite(final HttpServletRequest request, final HttpServletResponse response,
            final String tunnelUUID) throws GuacamoleException {

        final GuacamoleTunnel tunnel = getTunnel(tunnelUUID);

        // We still need to set the content type to avoid the default of
        // text/html, as such a content type would cause some browsers to
        // attempt to parse the result, even though the JavaScript client
        // does not explicitly request such parsing.
        response.setContentType("application/octet-stream");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentLength(0);

        // Send data
        try {

            // Get writer from tunnel
            final GuacamoleWriter writer = tunnel.acquireWriter();

            // Get input reader for HTTP stream
            final Reader input = new InputStreamReader(request.getInputStream(), "UTF-8");

            // Transfer data from input stream to tunnel output, ensuring
            // input is always closed
            try {

                // Buffer
                int length;
                final char[] buffer = new char[8192];

                // Transfer data using buffer
                while (tunnel.isOpen() && (length = input.read(buffer, 0, buffer.length)) != -1) {
                    writer.write(buffer, 0, length);
                }

            }

            // Close input stream in all cases
            finally {
                input.close();
            }

        } catch (final GuacamoleConnectionClosedException e) {
            logger.debug("Connection to guacd closed.", e);
        } catch (final IOException e) {

            // Deregister and close
            deregisterTunnel(tunnel);
            tunnel.close();

            throw new GuacamoleServerException("I/O Error sending data to server: " + e.getMessage(), e);
        } finally {
            tunnel.releaseWriter();
        }

    }

    @Override
    public void destroy() {
        tunnels.shutdown();
    }

}

/**
 * \example ExampleTunnelServlet.java
 *
 * A basic example demonstrating extending GuacamoleTunnelServlet and implementing doConnect() to configure the
 * Guacamole connection as desired.
 */
