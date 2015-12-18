package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html;

import javax.annotation.concurrent.Immutable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import de.invesdwin.util.lang.Strings;

@Immutable
public final class Documents {

    private Documents() {}

    public static String toString(final Document doc) {
        doc.outputSettings().indentAmount(4);
        doc.outputSettings().prettyPrint(true);
        String s = doc.html();
        /*
         * wicket expects closing tags to be different to what jsoup produces
         * 
         * otherwise radio inputs will not work properly
         */
        for (final String fixCloseTag : new String[] { "input" }) {
            for (final Element element : doc.getElementsByTag(fixCloseTag)) {
                final String elementHtml = element.outerHtml();
                if (elementHtml.endsWith("/>")) {
                    final String fixedElementHtml = Strings.removeAnyEnd(elementHtml, " />", "/>") + "></"
                            + fixCloseTag + ">";
                    s = s.replace(elementHtml, fixedElementHtml);
                }
            }
        }
        return s;
    }

}
