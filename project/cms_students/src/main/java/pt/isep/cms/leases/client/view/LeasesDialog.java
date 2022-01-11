package pt.isep.cms.leases.client.view;

import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.leases.client.presenter.EditLeasePresenter;
import pt.isep.cms.leases.client.view.LeasesDialog;

public class LeasesDialog implements EditLeasePresenter.Display{
    public enum Type {
        ADD,
        UPDATE
    }

    /**
     * The constants used in this Content Widget.
     */
    public static interface CwConstants extends Constants {

        String cwAddLeaseDialogCaption();

        String cwUpdateLeaseDialogCaption();

//		String cwDialogBoxClose();
//
//		String cwDialogBoxDescription();
//
//		String cwDialogBoxDetails();
//
//		String cwDialogBoxItem();
//
//		String cwDialogBoxListBoxInfo();
//
//		String cwDialogBoxMakeTransparent();
//
//		String cwDialogBoxName();
//
//		String cwDialogBoxShowButton();
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    // Widgets
    private final DateBox onDate;
    private final DateBox toDate;
    private final TextBox book;
    private final TextBox leasesContacts;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    private void initDetailsTable() {
        detailsTable.setWidget(0, 0, new Label("On Date"));
        detailsTable.setWidget(0, 1, onDate);
        detailsTable.setWidget(1, 0, new Label("To Date"));
        detailsTable.setWidget(1, 1, toDate);
        detailsTable.setWidget(2, 0, new Label("Book"));
        detailsTable.setWidget(2, 1, book);
        detailsTable.setWidget(3, 0, new Label("Contact"));
        detailsTable.setWidget(3, 1, leasesContacts);
        book.setFocus(true);
    }

    DecoratorPanel contentDetailsDecorator;
    final DialogBox dialogBox;

    /**
     * Constructor.
     *
     * @param constants
     *            the constants
     */
    public LeasesDialog(ShowcaseConstants constants, LeasesDialog.Type type) {
        // super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

        this.constants = constants;
        this.globalConstants = constants;

        // Init the widgets of the dialog
        contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("30em"); // em = size of current font
        // initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");

        // Create the leases list
        //
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(0);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("leases-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-lease-input");
        onDate = new DateBox();
        toDate = new DateBox();
        book = new TextBox();
        leasesContacts = new TextBox();
        initDetailsTable();
        contentDetailsPanel.add(detailsTable);

        HorizontalPanel menuPanel = new HorizontalPanel();
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        menuPanel.add(saveButton);
        menuPanel.add(cancelButton);
        contentDetailsPanel.add(menuPanel);
        contentDetailsDecorator.add(contentDetailsPanel);

        dialogBox = new DialogBox();
        dialogBox.ensureDebugId("cwDialogBox");
        if (type== LeasesDialog.Type.ADD)
            dialogBox.setText(constants.cwAddLeaseDialogCaption());
        else
            dialogBox.setText(constants.cwUpdateLeaseDialogCaption());

        dialogBox.add(contentDetailsDecorator);

        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);
    }

    public void displayDialog() {
        // Create the dialog box
        // final DialogBox dialogBox = createDialogBox();

        dialogBox.center();
        dialogBox.show();
    }

    @Override
    public HasClickHandlers getSaveButton() {
        // TODO Auto-generated method stub
        return saveButton;
        // return null;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        // TODO Auto-generated method stub
        return cancelButton;
        // return null;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        // return null;
        displayDialog();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        // return null;
        dialogBox.hide();
    }

    @Override
    public HasValue<Date> getOnDate() {
        // TODO Auto-generated method stub
        return onDate;
    }

    @Override
    public HasValue<Date> getToDate() {
        // TODO Auto-generated method stub
        return toDate;
    }

    @Override
    public HasValue<String> getBook() {
        // TODO Auto-generated method stub
        return book;
    }

    @Override
    public HasValue<String> getLeasesContact() {
        return leasesContacts;
    }
}
