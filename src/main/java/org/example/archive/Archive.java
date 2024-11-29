package org.example.archive;

import org.example.products.book.Book;
import org.example.products.book.Genre;
import org.example.products.magazine.Magazine;
import org.example.products.magazine.Periodicity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Archive implements ArchiveMethods{

    private final List<Book> BOOKS = Arrays.asList(
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
    );


    private final List<Magazine> MAGAZINES = Arrays.asList(
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
    );

    // BOOKS METHODS

    @Override
    public void addBook(int isbn, String title, int year, int pages, String author, Genre genre) {
    Book newBook = new Book(isbn, title, year, pages, author, genre);
    BOOKS.add(newBook);
    System.out.println(newBook.getTitle() + " add to list");
    }

    @Override
    public void searchBookIsbn(int isbn) {
        Book searched = BOOKS.stream()
                .filter(book -> book.getIsbn() == isbn)
                .findFirst()
                .orElse(null);
        if (searched != null) {
            System.out.println(searched);
        } else {
            System.out.println("This book don't exist");
        }
    }

    @Override
    public void removeBookIsbn(int isbn) {
    BOOKS.remove(isbn);
    }

    @Override
    public void searchBookForYear(int year) {
        List<Book> searched = BOOKS.stream()
                .filter(book -> book.getYear() == year)
                .sorted(Comparator.comparing(Book::getTitle))
                .toList();

        if (!searched.isEmpty()) {
            System.out.println("Books founded for " + year + ":");
            searched.forEach(System.out::println);
        } else {
            System.out.println("Books not founds for " + year);
        }
    }


    @Override
    public void searchBookForAuthor(String author) {
    List<Book> searched = BOOKS.stream()
            .filter(book -> book.getAuthor().equals(author))
            .sorted(Comparator.comparing(Book::getTitle))
            .toList();

    if (!searched.isEmpty()) {
        System.out.println("Books founded for " + author + ":");
        searched.forEach(System.out::println);
    } else {
        System.out.println("Books not founds for " + author);
    }

    }

    @Override
    public boolean updateBook(int isbn, String newTitle, int newYear, int newPages, String newAuthor, Genre newGenre) {
        Book bookToUpdate = BOOKS.stream()
                .filter(book -> book.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if (bookToUpdate != null) {
            bookToUpdate.setTitle(newTitle);
            bookToUpdate.setYear(newYear);
            bookToUpdate.setPages(newPages);
            bookToUpdate.setAuthor(newAuthor);
            bookToUpdate.setGenre(newGenre);
            System.out.println("Updated book: " + bookToUpdate);
            return true;
        } else {
            System.out.println("Book not found: " + isbn);
            return false;
        }
 }


    // MAGAZINE METHODS

    @Override
    public void addMagazine(int isbn, String title, int year, int pages, Periodicity periodicity) {
        Magazine newMagazine = new Magazine(isbn, title, year, pages, periodicity);
        MAGAZINES.add(newMagazine);
        System.out.println(newMagazine.getTitle() + " added to the list");
    }

    @Override
    public void searchMagazineIsbn(int isbn) {
        Magazine searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if (searched != null) {
            System.out.println(searched);
        } else {
            System.out.println("This magazine doesn't exist");
        }
    }

    @Override
    public void removeMagazineIsbn(int isbn) {
        MAGAZINES.removeIf(magazine -> magazine.getIsbn() == isbn);
        System.out.println("Magazine with ISBN " + isbn + " removed (if it existed)");
    }

    @Override
    public void searchMagazineForYear(int year) {
        List<Magazine> searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getYear() == year)
                .sorted(Comparator.comparing(Magazine::getTitle))
                .toList();

        if (!searched.isEmpty()) {
            System.out.println("Magazines found for year " + year + ":");
            searched.forEach(System.out::println);
        } else {
            System.out.println("No magazines found for year " + year);
        }
    }

    @Override
    public boolean updateMagazine(int isbn, String newTitle, int newYear, int newPages, Periodicity newPeriodicity) {
        Magazine magazineToUpdate = MAGAZINES.stream()
                .filter(magazine -> magazine.getIsbn() == isbn)
                .findFirst()
                .orElse(null);

        if (magazineToUpdate != null) {
            magazineToUpdate.setTitle(newTitle);
            magazineToUpdate.setYear(newYear);
            magazineToUpdate.setPages(newPages);
            magazineToUpdate.setPeriodicity(newPeriodicity);
            System.out.println("Updated magazine: " + magazineToUpdate);
            return true;
        } else {
            System.out.println("Magazine not found: " + isbn);
            return false;
        }
    }

    @Override
    public void searchMagazineForPeriodicity(Periodicity periodicity) {
        List<Magazine> searched = MAGAZINES.stream()
                .filter(magazine -> magazine.getPeriodicity() == periodicity)
                .sorted(Comparator.comparing(Magazine::getTitle))
                .toList();

        if (!searched.isEmpty()) {
            System.out.println("Magazines with periodicity " + periodicity + ":");
            searched.forEach(System.out::println);
        } else {
            System.out.println("No magazines found with periodicity " + periodicity);
        }
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
        System.out.println("Catalog statistics:");
        System.out.println("Total books: " + totalBooks);
        System.out.println("Total magazines: " + totalMagazines);
        if (maxPagesElement != null) {
            System.out.println("Element with the most pages: " + maxPagesElement);
        }
        System.out.println("Average pages across all elements: " + averagePages);
    }

}
