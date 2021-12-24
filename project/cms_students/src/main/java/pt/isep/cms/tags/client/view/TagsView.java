package pt.isep.cms.tags.client.view;

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
import pt.isep.cms.tags.client.presenter.TagsPresenter;

import java.util.ArrayList;
import java.util.List;

public class TagsView extends Composite implements TagsPresenter.Display {
    private final Button addButton;
    private final Button deleteButton;
    private FlexTable tagsTable;
    private final FlexTable contentTable;
    // private final VerticalPanel vPanel ;

    public TagsView() {
        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("18em");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");
        contentTable.getCellFormatter().addStyleName(0, 0, "tags-ListContainer");
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

        contentTable.getCellFormatter().addStyleName(0, 0, "generic-ListMenu");
        contentTable.setWidget(0, 0, hPanel);

        // Create the tags list
        //
        tagsTable = new FlexTable();
        tagsTable.setCellSpacing(0);
        tagsTable.setCellPadding(0);
        tagsTable.setWidth("100%");
        tagsTable.addStyleName("generic-ListContents");
        tagsTable.getColumnFormatter().setWidth(0, "15px");

        // vPanel.add(tagsTable);

        contentTable.setWidget(1, 0, tagsTable);

        contentTableDecorator.add(contentTable);
    }

    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    public HasClickHandlers getList() {
        return tagsTable;
    }

    public void setData(List<String> data) {
        tagsTable.removeAllRows();

        for (int i = 0; i < data.size(); ++i) {
            tagsTable.setWidget(i, 0, new CheckBox());
            tagsTable.setText(i, 1, data.get(i));
        }
    }

    public int getClickedRow(ClickEvent event) {
        int selectedRow = -1;
        HTMLTable.Cell cell = tagsTable.getCellForEvent(event);

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

        for (int i = 0; i < tagsTable.getRowCount(); ++i) {
            CheckBox checkBox = (CheckBox) tagsTable.getWidget(i, 0);
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
