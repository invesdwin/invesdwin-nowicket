package de.invesdwin.nowicket.component.logviewer;

import de.invesdwin.norva.marker.ISerializableValueObject;
import de.invesdwin.util.collections.iterable.ICloseableIterable;
import de.invesdwin.util.time.fdate.FDate;

public interface ILogViewerSource extends ISerializableValueObject {

    ICloseableIterable<LogViewerEntry> getLogViewerEntries(FDate from, Integer tailCount);

}
