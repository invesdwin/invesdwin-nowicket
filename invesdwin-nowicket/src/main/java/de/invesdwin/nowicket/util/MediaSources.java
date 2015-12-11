package de.invesdwin.nowicket.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.wicketstuff.html5.media.MediaSource;

@Immutable
public final class MediaSources {

    /**
     * MP4 needs to be the first one: http://diveintohtml5.info/video.html#ios
     * 
     * General infos: http://stackoverflow.com/questions/17045961/how-to-embed-an-m4v-video-file-to-my-html-webpage
     */
    private static final Map<String, String> COMMON_MEDIA_FORMATS = new LinkedHashMap<String, String>();
    static {
        COMMON_MEDIA_FORMATS.put(".mp4", "video/mp4");
        COMMON_MEDIA_FORMATS.put(".ogv", "video/ogg");
        COMMON_MEDIA_FORMATS.put(".webm", "video/webm");
    }

    private MediaSources() {}

    public static List<MediaSource> getCommonMediaSources(final Class<?> scope, final String fileBaseName) {
        final List<MediaSource> videos = new ArrayList<MediaSource>();
        for (final Entry<String, String> e : COMMON_MEDIA_FORMATS.entrySet()) {
            final ResourceReference videoRef = new PackageResourceReference(scope, fileBaseName + e.getKey());
            final String urlForVideoRef = RequestCycle.get().urlFor(videoRef, null).toString();
            videos.add(new MediaSource(urlForVideoRef, e.getValue()));
        }
        return videos;
    }

}
