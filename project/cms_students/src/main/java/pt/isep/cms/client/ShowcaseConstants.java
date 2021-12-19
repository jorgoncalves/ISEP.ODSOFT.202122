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
package pt.isep.cms.client;

import pt.isep.cms.client.MainMenuTreeViewModel.MenuConstants;

import pt.isep.cms.books.client.BooksController;
import pt.isep.cms.books.client.view.BooksDialog;
import pt.isep.cms.books.client.CwBooks;

import pt.isep.cms.bookmarks.client.BookmarksController;
import pt.isep.cms.bookmarks.client.CwBookmarks;
import pt.isep.cms.contacts.client.CwContacts;
import pt.isep.cms.bookmarks.client.view.BookmarksDialog;
import pt.isep.cms.contacts.client.view.ContactsDialog;
import pt.isep.cms.contacts.client.ContactsController;

/**
 * Constants used throughout the showcase.
 */
public interface ShowcaseConstants extends MenuConstants,
        CwBookmarks.CwConstants, BookmarksDialog.CwConstants, BookmarksController.CwConstants,
        CwBooks.CwConstants, BooksDialog.CwConstants,BooksController.CwConstants,
        CwContacts.CwConstants, ContactsDialog.CwConstants, ContactsController.CwConstants {

    /**
     * The path to source code for examples, raw files, and style definitions.
     */
    // String DST_SOURCE = "gwtShowcaseSource/";

    /**
     * The destination folder for parsed source code from Showcase examples.
     */
    // String DST_SOURCE_EXAMPLE = DST_SOURCE + "java/";

    /**
     * The destination folder for raw files that are included in entirety.
     */
    // String DST_SOURCE_RAW = DST_SOURCE + "raw/";

    /**
     * The destination folder for parsed CSS styles used in Showcase examples.
     */
    // String DST_SOURCE_STYLE = DST_SOURCE + "css/";
}
