package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.books.shared.Book;

public class BookUpdatedEvent extends GwtEvent<BookUpdatedEventHandler>{
  public static Type<BookUpdatedEventHandler> TYPE = new Type<BookUpdatedEventHandler>();
  private final Book updatedBook;
  
  public BookUpdatedEvent(Book updatedBook) {
    this.updatedBook = updatedBook;
  }
  
  public Book getUpdatedBook() { return updatedBook; }
  

  @Override
  public Type<BookUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BookUpdatedEventHandler handler) {
    handler.onBookUpdated(this);
  }
}
