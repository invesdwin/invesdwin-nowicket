package de.invesdwin.nowicket.examples.guide.pages.documentation.introduction;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.CarouselImage;
import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.ICarouselImage;
import de.invesdwin.nowicket.examples.guide.pages.documentation.concept.Concept;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Introduction extends AValueObject {

    public Concept readFirstChapter() {
        return new Concept();
    }

    public List<ICarouselImage> getFrameworkSloganImgs() {
        final List<ICarouselImage> imgs = new ArrayList<ICarouselImage>();
        for (int i = 1; i <= 5; i++) {
            final String path = "FrameworkSlogan_" + i + ".jpg";
            final PackageResourceReference resource = new PackageResourceReference(getClass(), path);
            final Url url = RequestCycle.get().mapUrlFor(resource, null);
            imgs.add(new CarouselImage(url.toString()));
        }
        return imgs;
    }

}
