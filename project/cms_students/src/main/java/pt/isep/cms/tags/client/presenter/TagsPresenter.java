package pt.isep.cms.tags.client.presenter;

import pt.isep.cms.tags.client.TagsServiceAsync;
import pt.isep.cms.tags.client.event.AddTagEvent;
import pt.isep.cms.tags.client.event.EditTagEvent;
import pt.isep.cms.tags.shared.TagDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class TagsPresenter implements Presenter {

	private List<TagDetails> tagDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final TagsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public TagsPresenter(TagsServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddTagEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedTags();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = tagDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditTagEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchTagDetails();
	}

	public void sortTagDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < tagDetails.size(); ++i) {
			for (int j = 0; j < tagDetails.size() - 1; ++j) {
				if (tagDetails.get(j).getDisplayName()
						.compareToIgnoreCase(tagDetails.get(j + 1).getDisplayName()) >= 0) {
					TagDetails tmp = tagDetails.get(j);
					tagDetails.set(j, tagDetails.get(j + 1));
					tagDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setTagDetails(List<TagDetails> tagDetails) {
		this.tagDetails = tagDetails;
	}

	public TagDetails getTagDetail(int index) {
		return tagDetails.get(index);
	}

	private void fetchTagDetails() {
		rpcService.getTagDetails(new AsyncCallback<ArrayList<TagDetails>>() {
			public void onSuccess(ArrayList<TagDetails> result) {
				tagDetails = result;
				sortTagDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(tagDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching tag details");
			}
		});
	}

	private void deleteSelectedTags() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(tagDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteTags(ids, new AsyncCallback<ArrayList<TagDetails>>() {
			public void onSuccess(ArrayList<TagDetails> result) {
				tagDetails = result;
				sortTagDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(tagDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected tags");
			}
		});
	}
}
