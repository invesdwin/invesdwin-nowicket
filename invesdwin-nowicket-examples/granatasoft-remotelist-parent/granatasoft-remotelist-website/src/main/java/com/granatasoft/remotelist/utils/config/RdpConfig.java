package com.granatasoft.remotelist.utils.config;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.CharSequenceResource;
import org.apache.wicket.request.resource.IResource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.invesdwin.context.log.error.Err;
import de.invesdwin.util.streams.pool.PooledFastByteArrayOutputStream;

@NotThreadSafe
public class RdpConfig implements IRemoteConfig {
    /***
     * Determines whether the remote session window appears full screen when you connect to the remote computer.
     * 
     * 1 - The remote session will appear in a window. 2 - The remote session will appear full screen.
     */
    private final String screenModeId = "1";
    /***
     * Determines whether the session should use true multiple monitor support when connecting to the remote computer.
     * 
     * 0 - Do not enable multiple monitor support. 1 - Enab le multiple monitor support
     */
    private final String useMultimon = "1";
    /***
     * Determines the color depth (in bits) on the remote computer when you connect.
     * 
     * 8 - 256 colors (8 bit). 15 - High color (15 bit). 16 - High color (16 bit). 24 - True color (24 bit). 32 -
     * Highest quality (32 bit).
     */
    private final String sessionBpp = "32";
    /***
     * Specifies the name or IP address (and optional port) of the remote computer that you want to connect to.
     */
    private final String fullAddress;
    /***
     * Determines how sounds on a remote computer are handled when you are connected to the remote computer.
     * 
     * 0 - Play sounds on the local computer. 1 - Play sounds on the remote computer. 2 - Do not play sounds.
     */
    private final String audioMode = "0";
    /***
     * Determines whether the desktop background is displayed in the remote session.
     *
     * 0 - Display the wallpaper. 1 - Do not show any wallpaper.
     */
    private final String disableWallpaper = "1";
    /***
     * Determines whether window content is displayed when you drag the window to a new location.
     *
     * 0 - Show the contents of the window while dragging. 1 - Show an outline of the window while dragging.
     */
    private final String disableFullWindowDrag = "0";
    /***
     * Determines whether menus and windows can be displayed with animation effects in the remote session.
     *
     * 0 - Menu and window animation is permitted. 1 - No menu and window animation.
     */
    private final String disableMenuAnims = "0";
    /***
     * Determines whether themes are permitted when you log on to the remote computer.
     *
     * 0 - Themes are permitted. 1 - Disable theme in the remote session.
     */
    private final String disableThemes = "0";
    /***
     * Specifies a program to be started automatically when you connect to a remote computer. The value should be a
     * valid path to an executable file. This setting only works when connecting to servers.
     */
    private final String alternateShell = null;
    /***
     * The working directory on the remote computer to be used if an alternate shell is specified.
     */
    private final String shellWorkingDirectory = null;
    /***
     * Determines what should happen when server authentication fails.
     *
     * 0 - If server authentication fails, connect without giving a warning. 1 - If server authentication fails, do not
     * connect. 2 - If server authentication fails, show a warning and allow the user to connect or not. 3 - Server
     * authentication is not required.
     */
    private final String authenticationLevel = "2";
    /***
     * Connect to the console session of the remote computer.
     *
     * 0 - Connect to a normal session. 1 - Connect to the console screen.
     */
    private final String connectToConsole = "0";
    /***
     * Specifies if and how to use a Remote Desktop Gateway (RD Gateway) server.
     *
     * 0 - Do not use an RD Gateway server. 1 - Always use an RD Gateway, even for local connections. 2 - Use the RD
     * Gateway if a direct connection cannot be made to the remote computer (i.e. bypass for local addresses). 3 - Use
     * the default RD Gateway settings. 4 - Do not use an RD Gateway server.
     *
     * 0 and 4 have the same effect, but setting the method to 4 also sets the option for bypassing local addresses in
     * the Remote Desktop user interface.
     */
    private final String gatewayUsageMethod = "0";
    //TODO check if this Mac only?
    private final String disableCursorSetting = "0";
    /***
     * Determines whether font smoothing may be used in the remote session.
     *
     * 0 - Disable font smoothing in the remote session. 1 - Font smoothing is permitted.
     */
    private final String allowFontSmoothing = "1";
    /***
     * Determines whether desktop composition (needed for Aero) is permitted when you log on to the remote computer.
     *
     * 0 - Disable desktop composition in the remote session. 1 - Desktop composition is permitted.
     */
    private final String allowDesktopComposition = "1";
    /***
     * Determines whether printers configured on the client computer will be redirected and available in the remote
     * session.
     *
     * 0 - The printers on the local computer are not available in the remote session. 1 - The printers on the local
     * computer are available in the remote session.
     */
    private final String redirectPrinters = "0";
    //TODO check if this Mac only?
    private final String bookmarkType = "3";
    //TODO check if this Mac only?
    private final String useRedirectionServerName = "0";
    /***
     * Specifies the name of the user account that will be used to log on to the remote computer.
     */
    private String username;

    public RdpConfig(final String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public RdpConfig(final String fullAddress, final String username) {
        this(fullAddress);
        this.username = username;
    }

    @Override
    public IResource getResource() {
        return new CharSequenceResource("application/text", this.toString(), this.fullAddress + ".rdp");
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("screen mode id:i:" + screenModeId + "\n");
        stringBuilder.append("use multimon:i:" + useMultimon + "\n");
        stringBuilder.append("session bpp:i:" + sessionBpp + "\n");
        stringBuilder.append("full address:s:" + fullAddress + "\n");
        stringBuilder.append("audiomode:i:" + audioMode + "\n");
        stringBuilder.append("username:s:" + username + "\n");
        stringBuilder.append("disable wallpaper:i:" + disableWallpaper + "\n");
        stringBuilder.append("disable full window drag:i:" + disableFullWindowDrag + "\n");
        stringBuilder.append("disable menu anims:i:" + disableMenuAnims + "\n");
        stringBuilder.append("disable themes:i:" + disableThemes + "\n");
        stringBuilder.append("alternate shell:s:" + "\n");
        stringBuilder.append("shell working directory:s:" + "\n");
        stringBuilder.append("authentication level:i:" + authenticationLevel + "\n");
        stringBuilder.append("connect to console:i:" + connectToConsole + "\n");
        stringBuilder.append("gatewayusagemethod:i:" + gatewayUsageMethod + "\n");
        stringBuilder.append("disable cursor setting:i:" + disableCursorSetting + "\n");
        stringBuilder.append("allow font smoothing:i:" + allowFontSmoothing + "\n");
        stringBuilder.append("allow desktop composition:i:" + allowDesktopComposition + "\n");
        stringBuilder.append("redirectprinters:i:" + redirectPrinters + "\n");
        stringBuilder.append("bookmarktype:i:" + bookmarkType + "\n");
        stringBuilder.append("use redirection server name:i:" + useRedirectionServerName + "\n");

        return stringBuilder.toString();
    }

    @Override
    public String toJSON() {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try (PooledFastByteArrayOutputStream stream = PooledFastByteArrayOutputStream.newInstance()) {
            String json = null;
            try {
                mapper.writeValue(stream, this);
                json = stream.toString();
            } catch (final IOException e) {
                Err.process(e);
            }
            //TODO serialize Object as JSON
            return json;
        }
    }
}
