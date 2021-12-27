package pt.isep.cms.tags.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.tags.client.TagsServiceAsync;
import pt.isep.cms.tags.client.event.TagUpdatedEvent;
import pt.isep.cms.tags.client.event.EditTagCancelledEvent;
import pt.isep.cms.tags.shared.Tag;

public class EditTagPresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getDescription();

        void show();

        void hide();
    }

    private Tag tag;
    private final TagsServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditTagPresenter(TagsServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.tag = new Tag();
        this.display = display;
        bind();
    }

    public EditTagPresenter(TagsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getTag(id, new AsyncCallback<Tag>() {
            public void onSuccess(Tag result) {
                tag = result;
                EditTagPresenter.this.display.getDescription().setValue(tag.getDescription());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving tag");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
                display.hide();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                display.hide();
                eventBus.fireEvent(new EditTagCancelledEvent());
            }
        });
    }

    public void go(final HasWidgets container) {
        display.show();
    }

    private void doSave() {
        tag.setDescription(display.getDescription().getValue());

        if (tag.getId() == null) {
            // Adding new tag
            rpcService.addTag(tag, new AsyncCallback<Tag>() {
                public void onSuccess(Tag result) {
                    eventBus.fireEvent(new TagUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error adding tag");
                }
            });
        } else {
            // updating existing tag
            rpcService.updateTag(tag, new AsyncCallback<Tag>() {
                public void onSuccess(Tag result) {
                    eventBus.fireEvent(new TagUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error updating tag");
                }
            });
        }
    }

}
