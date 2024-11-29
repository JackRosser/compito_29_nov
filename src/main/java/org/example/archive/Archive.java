package org.example.archive;

import org.example.exceptions.BookNotFoundException;
import org.example.exceptions.DuplicateItemException;
import org.example.exceptions.MagazineNotFoundException;
import org.example.products.book.Book;
import org.example.products.book.Genre;
import org.example.products.magazine.Magazine;
import org.example.products.magazine.Periodicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Archive implements ArchiveMethods{
    private static Archive instance;
    private final List<Book> BOOKS = new ArrayList<>(List.of(
            new Book(12345, "1984", 1949, 328, "George Orwell", Genre.DYSTOPIAN),
            new Book(67890, "To Kill a Mockingbird", 1960, 281, "Harper Lee", Genre.FICTION),
            new Book(11121, "The Great Gatsby", 1925, 180, "F. Scott Fitzgerald", Genre.CLASSIC),
            new Book(22232, "Pride and Prejudice", 1813, 279, "Jane Austen", Genre.ROMANCE),
            new Book(33343, "The Catcher in the Rye", 1951, 214, "J.D. Salinger", Genre.FICTION),
            new Book(44454, "The Hobbit", 1937, 310, "J.R.R. Tolkien", Genre.FANTASY),
            new Book(55565, "Brave New World", 1932, 311, "Aldous Huxley", Genre.DYSTOPIAN),
            new Book(66676, "Moby Dick", 1851, 635, "Herman Melville", Genre.ADVENTURE),
            new Book(77787, "War and Peace", 1869, 1225, "Leo Tolstoy", Genre.HISTORICAL),
            new Book(88898, "Crime and Punishment", 1866, 671, "Fyodor Dostoevsky", Genre.PSYCOLOGICAL)
    ));


    private final List<Magazine> MAGAZINES = new ArrayList<>(List.of(
            new Magazine(1001, "Tech Today", 2023, 48, Periodicity.MONTHLY),
            new Magazine(1002, "Health & Wellness", 2023, 64, Periodicity.WEEKLY),
            new Magazine(1003, "Traveler's Guide", 2022, 96, Periodicity.MONTHLY),
            new Magazine(1004, "Science World", 2023, 128, Periodicity.SEMIANNUALY),
            new Magazine(1005, "Cooking Delight", 2023, 72, Periodicity.WEEKLY),
            new Magazine(1006, "History Digest", 2022, 80, Periodicity.MONTHLY),
            new Magazine(1007, "Fitness Journal", 2023, 64, Periodicity.WEEKLY),
            new Magazine(1008, "Fashion Forward", 2023, 120, Periodicity.MONTHLY),
            new Magazine(1009, "Automotive Trends", 2022, 88, Periodicity.SEMIANNUALY),
            new Magazine(1010, "Music Insider", 2023, 52, Periodicity.WEEKLY)
    ));

    private Archive() {}

    public static Archive getInstance() {
        if (instance == null) {
            instance = new Archive();
        }
        return instance;
    }

    // BOOKS METHODS

    @Override
    public void addBook(int isbn, String title, int year, int pages, String author, Genre genre) {
        boolean exists = BOOKS.stream().anyMatch(book -> book.getIsbn() == isbn);
        if (exists) {
            throw new DuplicateItemException("Book with ISBN " + isbn + " already exists.");
        }
        Book newBook = new Book(isbn, title, year, pages, author, genre);
        BOOKS.add(newBook);
        System.out.println(newBook.getTitle() + " added to the list.");
    }


    @Override
    public void searchBookIsbn(int isbn) {
        Book searched = BOOKS.stream()
                .filter(book -> book.getIsbn() == isbn)
                .findFirst()
                .orElseThrow(()-> new BookNotFoundException("Book with ISBN " + isbn + " not found."));
        System.out.println(searched);
    }

    @Override
    public void removeBookIsbn(int isbn) {
        boolean removed = BOOKS.removeIf(book -> book.getIsbn() == isbn);
        if (!removed) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found. Cannot remove.");
        } else {
            System.out.println("Book with ISBN " + isbn + " removed successfully.");
        }
    }


    @Override
    public void searchBookForYear(int year) {
        List<Book> searched = BOOKS.stream()
                .filter(book -> book.getYear() == year)
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
        if (searched.isEmpty()) {throw new BookNotFoundException("No books found for the year " + year);}
        System.out.println("Books found for the year " + year);
        searched.forEach(System.out::println);

    }


    @Override
    public void searchBookForAuthor(String author) {
    List<Book> searched = BOOKS.stream()
            .filter(book -> book.getAuthor().equals(author))
            .sorted(Comparator.comparing(Book::getTitle))
            .toList();

if (searched.isEmpty()) {throw new BookNotFoundException("No books found for " + author);}
        System.out.println("Books found for " + author);
searched.forEach(System.out::println);

    }

    @Override
    public boolean updateBook(int isbn, String newTitle, int newYear, int newPages, String newAuthor, Genre newGenre) {
        for (int i = 0; i < BOOKS.size(); i++) {
            if (BOOKS.get(i).getIsbn() == isbn) {
                Book updatedBook = new Book(
                        isbn,
                        newTitle != null ? newTitle : BOOKS.get(i).getTitle(),
                        newYear != -1 ? newYear : BOOKS.get(i).getYear(),
                        newPages != -1 ? newPages : BOOKS.get(i).getPages(),
                        newAuthor != null ? newAuthor : BOOKS.get(i).getAuthor(),
                        newGenre != null ? newGenre : BOOKS.get(i).getGenre()
                );
                BOOKS.set(i, updatedBook);
                return true;
            }
        }
        return false;
    }




    // MAGAZINE METHODS

    @Override
    public void addMagazine(int isbn, String title, int year, int pages, Periodicity periodicity) {
boolean exists = MAGAZINES.stream().anyMatch(magazine -> magazine.getIsbn() == isbn);
if (exists) {
    throw new DuplicateItemException("Magazine with " + isbn + " already exists");
}
Magazine newMagazine = new Magazine(isbn, title, year, pages, periodicity);
MAGAZINES.add(newMagazine);
        System.out.println(newMagazine.getTitle() + " added to list");
    }

    @Override
    public void searchMagazineIsbn(int isbn) {
        Magazine searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getIsbn() == isbn)
                .findFirst()
                .orElseThrow(()-> new MagazineNotFoundException("Magazine with ISBN " + isbn + " not found."));
        System.out.println(searched);
    }

    @Override
    public void removeMagazineIsbn(int isbn) {
        boolean removed = MAGAZINES.removeIf(magazine -> magazine.getIsbn() == isbn);
        if (!removed) {
            throw new MagazineNotFoundException("Magazine with ISBN " + isbn + " not found.");
        }
    }


    @Override
    public void searchMagazineForYear(int year) {
        List<Magazine> searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getYear() == year)
                .sorted(Comparator.comparing(Magazine::getTitle))
                .collect(Collectors.toList());


        if (searched.isEmpty()) {throw new MagazineNotFoundException("Nothing magazines in this year");}
        System.out.println("Magazines for " + year);
searched.forEach(System.out::println);
    }

    @Override
    public boolean updateMagazine(int isbn, String newTitle, int newYear, int newPages, Periodicity newPeriodicity) {
        for (int i = 0; i < MAGAZINES.size(); i++) {
            if (MAGAZINES.get(i).getIsbn() == isbn) {
                Magazine updatedMagazine = new Magazine(
                        isbn,
                        newTitle != null ? newTitle : MAGAZINES.get(i).getTitle(),
                        newYear != -1 ? newYear : MAGAZINES.get(i).getYear(),
                        newPages != -1 ? newPages : MAGAZINES.get(i).getPages(),
                        newPeriodicity != null ? newPeriodicity : MAGAZINES.get(i).getPeriodicity()
                );
                MAGAZINES.set(i, updatedMagazine);
                return true;
            }
        }
        return false;
    }


    @Override
    public void searchMagazineForPeriodicity(Periodicity periodicity) {
        List<Magazine> searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getPeriodicity() == periodicity)
                .sorted(Comparator.comparing(Magazine::getTitle))
                .toList();

if(searched.isEmpty()) {throw new MagazineNotFoundException("Nothing magazine for " + periodicity);}
        System.out.println("Magazines found for " + periodicity);
searched.forEach(System.out::println);
    }


    @Override
    public void printAll() {
        // DEFINISCO IL NUMERO TOTALE DI ELEMENTI
        int totalBooks = BOOKS.size();
        int totalMagazines = MAGAZINES.size();

        // DEFINISCO L'ELEMENTO CON IL MAGGIOR NUMERO DI PAGINE
        // GLI DO VAR COME SCORCIATOIA
        var maxPagesBook = BOOKS.stream().max(Comparator.comparingInt(Book::getPages)).orElse(null);
        var maxPagesMagazine = MAGAZINES.stream().max(Comparator.comparingInt(Magazine::getPages)).orElse(null);

        String maxPagesElement = null;
        if (maxPagesBook != null && maxPagesMagazine != null) {
            maxPagesElement = (maxPagesBook.getPages() > maxPagesMagazine.getPages())
                    ? "Book: " + maxPagesBook.getTitle() + " with " + maxPagesBook.getPages() + " pages"
                    : "Magazine: " + maxPagesMagazine.getTitle() + " with " + maxPagesMagazine.getPages() + " pages";
        } else if (maxPagesBook != null) {
            maxPagesElement = "Book: " + maxPagesBook.getTitle() + " with " + maxPagesBook.getPages() + " pages";
        } else if (maxPagesMagazine != null) {
            maxPagesElement = "Magazine: " + maxPagesMagazine.getTitle() + " with " + maxPagesMagazine.getPages() + " pages";
        }

        // QUA FACCIO UNA MEDIA DELLE PAGINE DI TUTTI GLI ELEMENTI
        double averagePages = BOOKS.stream().mapToInt(Book::getPages).sum() +
                MAGAZINES.stream().mapToInt(Magazine::getPages).sum();
        averagePages /= (totalBooks + totalMagazines);

        // STAMPO I RISULTATI
        System.out.println("Statistiche:");
        System.out.println("Libri totali: " + totalBooks);
        System.out.println("Riviste totali: " + totalMagazines);
        if (maxPagesElement != null) {
            System.out.println("Elemento con maggior numero di pagine: " + maxPagesElement);
        }
        System.out.println("Media pagine: " + averagePages);
    }

}
