package grasp.highcohesion;

import java.util.HashMap;
import java.util.Map;

import common.Console;
import grasp.highcohesion.Domain.Book;

/** One job: the library returns desk. */
public class LibraryService {

    private static final int FINE_PER_DAY_UAH = 5;

    private final Map<String, String> shelved = new HashMap<>();

    public int shelveReturnedBook(Book book, int daysLate) {
        shelved.put(book.isbn(), book.title());
        int fine = Math.max(0, daysLate) * FINE_PER_DAY_UAH;
        Console.ok("shelved '" + book.title() + "'" + (fine > 0 ? " (fine " + fine + " UAH)" : ""));
        return fine;
    }
}
