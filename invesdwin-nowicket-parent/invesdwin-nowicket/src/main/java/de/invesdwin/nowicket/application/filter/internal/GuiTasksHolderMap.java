package de.invesdwin.nowicket.application.filter.internal;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.MetaDataKey;

import com.google.common.cache.CacheBuilder;

import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.UUIDs;

@ThreadSafe
public final class GuiTasksHolderMap {

    private static final MetaDataKey<GuiTasksHolderMap> MAP_KEY = new MetaDataKey<GuiTasksHolderMap>() {
    };

    private final Map<String, GuiTasksHolder> uuid_guiTasksHolder;

    private GuiTasksHolderMap() {
        uuid_guiTasksHolder = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(1000)
                .<String, GuiTasksHolder> build()
                .asMap();
    }

    public String put(final GuiTasksHolder guiTasksHolder) {
        final String uuid = UUIDs.newPseudorandomUUID();
        uuid_guiTasksHolder.put(uuid, Objects.clone(guiTasksHolder));
        return uuid;
    }

    public GuiTasksHolder get(final String uuid) {
        return uuid_guiTasksHolder.get(uuid);
    }

    public static GuiTasksHolderMap get() {
        final AWebApplication session = AWebApplication.get();
        synchronized (session) {
            GuiTasksHolderMap map = session.getMetaData(MAP_KEY);
            if (map == null) {
                map = new GuiTasksHolderMap();
                session.setMetaData(MAP_KEY, map);
            }
            return map;
        }
    }

}
