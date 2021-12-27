package pt.isep.cms.leases.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;
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
    private final TextBox firstName;
    private final TextBox lastName;
    private final TextBox emailAddress;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    private void initDetailsTable() {
        detailsTable.setWidget(0, 0, new Label("Firstname"));
        detailsTable.setWidget(0, 1, firstName);
        detailsTable.setWidget(1, 0, new Label("Lastname"));
        detailsTable.setWidget(1, 1, lastName);
        detailsTable.setWidget(2, 0, new Label("Email Address"));
        detailsTable.setWidget(2, 1, emailAddress);
        firstName.setFocus(true);
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
        firstName = new TextBox();
        lastName = new TextBox();
        emailAddress = new TextBox();
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
    public HasValue<String> getFirstName() {
        // TODO Auto-generated method stub
        return firstName;
        // return null;
    }

    @Override
    public HasValue<String> getLastName() {
        // TODO Auto-generated method stub
        return lastName;
        // return null;
    }

    @Override
    public HasValue<String> getEmailAddress() {
        // TODO Auto-generated method stub
        return emailAddress;
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
}
