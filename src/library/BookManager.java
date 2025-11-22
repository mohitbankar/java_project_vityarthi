package library;

import java.util.HashMap;
import java.util.Map;

public class BookManager {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    public boolean isAvailable(String bookId) {
        Book book = books.get(bookId);
        return book != null && book.isAvailable();
    }

    public void setAvailability(String bookId, boolean available) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setAvailable(available);
        }
    }
}
