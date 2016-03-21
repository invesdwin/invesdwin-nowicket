package de.invesdwin.nowicket.examples.guide.pages.documentation.installation;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.util.string.Strings;

import de.invesdwin.nowicket.examples.guide.pages.documentation.frameworkhistory.FrameworkHistory;
import de.invesdwin.nowicket.examples.guide.pages.documentation.tutorial.Tutorial;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Installation extends AValueObject {

    public FrameworkHistory goBackToPreviousChapter() {
        return new FrameworkHistory();
    }

    public Tutorial readNextChapter() {
        return new Tutorial();
    }

    public String getDirectoryLayoutImg() {
        return "InstallationDirectoryLayout.png";
    }

    public CharSequence getPomXmlContent() throws IOException {
        return getFileContent("ProjectSetupPom.xml");
    }

    public CharSequence getWebXmlContent() throws IOException {
        return getFileContent("ProjectSetupWeb.xml");
    }

    public CharSequence getPageHtmlContent() throws IOException {
        return getFileContent("ProjectSetupPage.html");
    }

    private CharSequence getFileContent(final String relativeClasspath) throws IOException {
        final InputStream in = getClass().getResourceAsStream(relativeClasspath);
        final String str = IOUtils.toString(in);
        return Strings.escapeMarkup(str.replace("\t", "    ").trim());
    }

}
