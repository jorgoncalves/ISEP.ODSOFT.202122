package pt.isep.cms.tags.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.tags.client.event.AddTagEvent;
import pt.isep.cms.tags.client.event.AddTagEventHandler;
import pt.isep.cms.tags.client.event.TagUpdatedEvent;
import pt.isep.cms.tags.client.event.TagUpdatedEventHandler;
import pt.isep.cms.tags.client.event.EditTagEvent;
import pt.isep.cms.tags.client.event.EditTagEventHandler;
import pt.isep.cms.tags.client.event.EditTagCancelledEvent;
import pt.isep.cms.tags.client.event.EditTagCancelledEventHandler;
import pt.isep.cms.tags.client.presenter.TagsPresenter;
import pt.isep.cms.tags.client.presenter.EditTagPresenter;
import pt.isep.cms.tags.client.presenter.Presenter;
import pt.isep.cms.tags.client.view.TagsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.tags.client.view.TagsDialog;

public class TagsController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final TagsServiceAsync rpcService;
    private HasWidgets container;

    public static interface CwConstants extends Constants {
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    public TagsController(TagsServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.constants = constants;
        this.globalConstants = constants;

        bind();
    }

    private void bind() {
        // (ATB) No History at this level
        // History.addValueChangeHandler(this);

        eventBus.addHandler(AddTagEvent.TYPE, new AddTagEventHandler() {
            public void onAddTag(AddTagEvent event) {
                doAddNewTag();
            }
        });

        eventBus.addHandler(EditTagEvent.TYPE, new EditTagEventHandler() {
            public void onEditTag(EditTagEvent event) {
                doEditTag(event.getId());
            }
        });

        eventBus.addHandler(EditTagCancelledEvent.TYPE, new EditTagCancelledEventHandler() {
            public void onEditTagCancelled(EditTagCancelledEvent event) {
                doEditTagCancelled();
            }
        });

        eventBus.addHandler(TagUpdatedEvent.TYPE, new TagUpdatedEventHandler() {
            public void onTagUpdated(TagUpdatedEvent event) {
                doTagUpdated();
            }
        });
    }

    private void doAddNewTag() {
        // Lets use the presenter to display a dialog...
        Presenter presenter = new EditTagPresenter(rpcService, eventBus,
                new TagsDialog(globalConstants, TagsDialog.Type.ADD));
        presenter.go(container);

    }

    private void doEditTag(String id) {
        Presenter presenter = new EditTagPresenter(rpcService, eventBus,
                new TagsDialog(globalConstants, TagsDialog.Type.UPDATE), id);
        presenter.go(container);
    }

    private void doEditTagCancelled() {
        // Nothing to update...
    }

    private void doTagUpdated() {
        // (ATB) Update the list of tags...
        Presenter presenter = new TagsPresenter(rpcService, eventBus, new TagsView());
        presenter.go(container);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        Presenter presenter = new TagsPresenter(rpcService, eventBus, new TagsView());
        presenter.go(container);
    }

}
