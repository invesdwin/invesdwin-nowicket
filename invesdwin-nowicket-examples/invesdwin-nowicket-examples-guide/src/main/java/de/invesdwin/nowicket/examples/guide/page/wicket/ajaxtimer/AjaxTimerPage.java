package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.lang3.RandomUtils;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.component.websocket.APreactAjaxTimerBehavior;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.context.ComponentRegistry;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTabbedPanel;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.FDates;
import de.invesdwin.util.time.duration.Duration;

@MountPath("ajaxtimer")
@NotThreadSafe
public class AjaxTimerPage extends AExampleWebPage {

    public AjaxTimerPage() {
        this(Model.of(new AjaxTimer()));
    }

    public AjaxTimerPage(final IModel<AjaxTimer> model) {
        super(model);
        new GeneratedBinding(this).addBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (e.getWicketId().equals(AjaxTimerConstants.tabs)) {
                    return new ModelTabbedPanel((ITabbedHtmlElement<?, ?>) e);
                }
                return super.create(e);
            }
        }).bind();
        add(new APreactAjaxTimerBehavior(Duration.ONE_SECOND) {

            private FDate prevLastRefresh = FDates.MIN_DATE;

            @Override
            protected void onTimer(final PreactPartialPageRequestHandler target) {
                final HtmlContext context = HtmlContext.get(AjaxTimerPage.this);
                final AjaxTimer model = (AjaxTimer) context.getMarkupContainer().getDefaultModel().getObject();
                //listen to the users preference
                if (!model.isRefreshAutomatically()) {
                    return;
                }
                //fake an update from an asynchronous process here
                if (RandomUtils.nextBoolean()) {
                    model.refresh();
                }
                //prevent processRequestFinally to update all forms
                GuiService.get().disableUpdateAllComponentsForCurrentRequest();
                try {
                    final FDate newLastRefresh = model.getLastRefresh();
                    final ComponentRegistry componentRegistry = context.getComponentRegistry();
                    //do some sort of check to prevent unnecessary updates
                    if (!newLastRefresh.equals(prevLastRefresh)) {
                        /*
                         * update the grid column instead of the tab itself, since that is the only component that gets
                         * a placeholder tab printed when the component is invisible
                         */
                        final Component tabs = componentRegistry.getComponent(
                                AjaxTimerConstants.tabs + GridColumnHtmlElement.GRID_COLUMN_WICKET_ID_SUFFIX);
                        target.add(tabs);
                        final Component lastRefresh = componentRegistry.getComponent(AjaxTimerConstants.lastRefresh);
                        target.add(lastRefresh);
                        prevLastRefresh = newLastRefresh;
                    }
                    final Component lastRefreshCheck = componentRegistry
                            .getComponent(AjaxTimerConstants.lastRefreshCheck);
                    target.add(lastRefreshCheck);
                } finally {
                    //process outstanding gui tasks if there are any
                    GuiService.get().processRequestFinally(AjaxTimerPage.this);
                }
            }

            @Override
            protected String findIndicatorId() {
                //disable the ajax indicator for these updates
                return null;
            }
        });
    }

}
