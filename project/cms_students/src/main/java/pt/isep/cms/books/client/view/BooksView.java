package pt.isep.cms.books.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import pt.isep.cms.books.client.presenter.*;
import java.util.ArrayList;
import java.util.List;

public class BooksView extends Composite implements BooksPresenter.Display {
	private final Button addButton;
	private final Button deleteButton;
	private FlexTable booksTable;
	private final FlexTable contentTable;
	// private final VerticalPanel vPanel ;

	public BooksView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0, "Books-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// vPanel = new VerticalPanel();
		// initWidget(vPanel);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);

		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);

		// vPanel.add(hPanel);

		contentTable.getCellFormatter().addStyleName(0, 0, "books-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// Create the Books list
		//
		booksTable = new FlexTable();
		booksTable.setCellSpacing(0);
		booksTable.setCellPadding(0);
		booksTable.setWidth("100%");
		booksTable.addStyleName("books-ListContents");
		booksTable.getColumnFormatter().setWidth(0, "15px");

		// vPanel.add(booksTable);

		contentTable.setWidget(1, 0, booksTable);

		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public HasClickHandlers getList() {
		return booksTable;
	}

	public void setData(List<String> data) {
		booksTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			booksTable.setWidget(i, 0, new CheckBox());
			booksTable.setText(i, 1, data.get(i));
		}
	}

	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = booksTable.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	public List<Integer> getSelectedRows() {
		List<Integer> selectedRows = new ArrayList<Integer>();

		for (int i = 0; i < booksTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) booksTable.getWidget(i, 0);
			if (checkBox.getValue()) {
				selectedRows.add(i);
			}
		}

		return selectedRows;
	}

	public Widget asWidget() {
		return this;
	}
}
