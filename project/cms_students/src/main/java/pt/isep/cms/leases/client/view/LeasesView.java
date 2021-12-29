package pt.isep.cms.leases.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import pt.isep.cms.leases.client.presenter.LeasesPresenter;

import java.util.ArrayList;
import java.util.List;

public class LeasesView extends Composite implements LeasesPresenter.Display{
    private final Button addButton;
    private final Button deleteButton;
    private FlexTable leasesTable;
    private final FlexTable contentTable;
    // private final VerticalPanel vPanel ;

    public LeasesView() {
        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("30em");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");
        contentTable.getCellFormatter().addStyleName(0, 0, "leases-ListContainer");
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

        contentTable.getCellFormatter().addStyleName(0, 0, "leases-ListMenu");
        contentTable.setWidget(0, 0, hPanel);

        // Create the leases list
        //
        leasesTable = new FlexTable();
        leasesTable.setCellSpacing(0);
        leasesTable.setCellPadding(0);
        leasesTable.setWidth("100%");
        leasesTable.addStyleName("leases-ListContents");
        leasesTable.getColumnFormatter().setWidth(0, "15px");

        // vPanel.add(leasesTable);

        contentTable.setWidget(1, 0, leasesTable);

        contentTableDecorator.add(contentTable);
    }

    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    public HasClickHandlers getList() {
        return leasesTable;
    }

    public void setData(List<String> data) {
        leasesTable.removeAllRows();

        for (int i = 0; i < data.size(); ++i) {
            leasesTable.setWidget(i, 0, new CheckBox());
            leasesTable.setText(i, 1, data.get(i));
        }
    }

    public int getClickedRow(ClickEvent event) {
        int selectedRow = -1;
        HTMLTable.Cell cell = leasesTable.getCellForEvent(event);

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

        for (int i = 0; i < leasesTable.getRowCount(); ++i) {
            CheckBox checkBox = (CheckBox) leasesTable.getWidget(i, 0);
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
