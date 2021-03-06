/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.isep.cms.books.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.books.client.presenter.EditBookPresenter;
import pt.isep.cms.tags.client.TagsService;
import pt.isep.cms.tags.client.TagsServiceAsync;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;

/**
 * Dialog Box for Adding and Updating Books.
 */
public class BooksDialog implements EditBookPresenter.Display {

    public enum Type {
        ADD,
        UPDATE
    }

    /**
     * The constants used in this Content Widget.
     */
    public static interface CwConstants extends Constants {

        String cwAddBookDialogCaption();

        String cwUpdateBookDialogCaption();

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
    private final TextBox title;
    private final TextBox author;
    private final TextBox isbn;
    private final ListBox tags;
    private final ListBox bookmarks;
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    private final TagsServiceAsync tagsRpcService;
    private final BookmarksServiceAsync bookmarksRpcService;

    private void initDetailsTable() {
        detailsTable.setWidget(0, 0, new Label("Tile"));
        detailsTable.setWidget(0, 1, title);
        detailsTable.setWidget(1, 0, new Label("Author"));
        detailsTable.setWidget(1, 1, author);
        detailsTable.setWidget(2, 0, new Label("ISBN"));
        detailsTable.setWidget(2, 1, isbn);
        detailsTable.setWidget(3, 0, new Label("Tags"));
        detailsTable.setWidget(3, 1, tags);
        detailsTable.setWidget(4, 0, new Label("Bookmarks"));
        detailsTable.setWidget(4, 1, bookmarks);
        title.setFocus(true);
    }

    DecoratorPanel contentDetailsDecorator;
    final DialogBox dialogBox;

    /**
     * Constructor.
     *
     * @param constants the constants
     */
    public BooksDialog(ShowcaseConstants constants, Type type, TagsServiceAsync tagsRpcService, BookmarksServiceAsync bookmarksRpcService) {
        this.tagsRpcService = tagsRpcService;
        this.bookmarksRpcService = bookmarksRpcService;

        // super(constants.cwDialogBoxName(), constants.cwDialogBoxDescription());

        this.constants = constants;
        this.globalConstants = constants;

        // Init the widgets of the dialog
        contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("60em"); // em = size of current font
        // initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");

        // Create the books list
        //
        detailsTable = new FlexTable();
        detailsTable.setCellSpacing(0);
        detailsTable.setWidth("100%");
        detailsTable.addStyleName("generic-ListContainer");
        detailsTable.getColumnFormatter().addStyleName(1, "add-generic-input");
        title = new TextBox();
        author = new TextBox();
        isbn = new TextBox();
        tags = new ListBox();
        tags.setMultipleSelect(true);
        addExistingTags();
        bookmarks = new ListBox();
        bookmarks.setMultipleSelect(true);
        addExistingBookmarks();
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
        if (type == Type.ADD)
            dialogBox.setText(constants.cwAddBookDialogCaption());
        else
            dialogBox.setText(constants.cwUpdateBookDialogCaption());

        dialogBox.add(contentDetailsDecorator);

        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);
    }

    private void addExistingTags() {
        tagsRpcService.getTagsDetails(
                new AsyncCallback<ArrayList<TagDetails>>() {
                    public void onSuccess(ArrayList<TagDetails> result) {
                        for (TagDetails tagDetails : result) {
                            tags.addItem(tagDetails.getDisplayName(), tagDetails.getId());
                        }
                    }

                    public void onFailure(Throwable caught) {
                        Window.alert("Error fetching tag details");
                    }
                }
        );

    }

    private void addExistingBookmarks() {
        bookmarksRpcService.getBookmarkDetails(
                new AsyncCallback<ArrayList<BookmarkDetails>>() {
                    public void onSuccess(ArrayList<BookmarkDetails> result) {
                        for (BookmarkDetails bookmarkDetails : result) {
                            bookmarks.addItem(bookmarkDetails.getDisplayName(), bookmarkDetails.getId());
                        }
                    }

                    public void onFailure(Throwable caught) {
                        Window.alert("Error fetching bookmark details");
                    }
                }
        );

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
    public HasValue<String> getTile() {
        // TODO Auto-generated method stub
        return title;
        // return null;
    }

    @Override
    public HasValue<String> getAuthor() {
        // TODO Auto-generated method stub
        return author;
        // return null;
    }

    @Override
    public HasValue<String> getISBN() {
        // TODO Auto-generated method stub
        return isbn;
        // return null;
    }

    @Override
    public ListBox getTags() {
        return tags;
    }

    @Override
    public ListBox getBookmarks() {
        return bookmarks;
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
