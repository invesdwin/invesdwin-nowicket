package de.invesdwin.nowicket.component.carousel;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.Carousel;
import de.agilecoders.wicket.core.markup.html.bootstrap.carousel.ICarouselImage;

/**
 * TODO: remove as soon as this is fixed upstream: https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/959
 */
@NotThreadSafe
public class ResponsiveCarousel extends Carousel {

    public ResponsiveCarousel(final String markupId, final IModel<List<ICarouselImage>> model) {
        super(markupId, model);
    }

    public ResponsiveCarousel(final String markupId, final List<ICarouselImage> images) {
        super(markupId, images);
    }

    @Override
    protected Component newImage(final String markupId, final ICarouselImage image) {
        final Component img = super.newImage(markupId, image);
        img.add(new AttributeModifier("class", "d-block w-100"));
        return img;
    }

}
