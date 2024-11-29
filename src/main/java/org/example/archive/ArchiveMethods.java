package org.example.archive;

import org.example.products.book.Genre;
import org.example.products.magazine.Periodicity;

public interface ArchiveMethods {

    // BOOKS

    void addBook(int isbn, String title, int year, int pages, String author, Genre genre);
    void searchBookIsbn(int isbn);
    void removeBookIsbn(int isbn);
    void searchBookForYear(int year);
    void searchBookForAuthor(String author);
    boolean updateBook(int isbn, String newTitle, int newYear, int newPages, String newAuthor, Genre newGenre);


    // MAGAZINES

    void addMagazine(int isbn, String title, int year, int pages, Periodicity periodicity);
    void searchMagazineIsbn(int isbn);
    void removeMagazineIsbn(int isbn);
    void searchMagazineForPeriodicity(Periodicity periodicity);
    void searchMagazineForYear(int year);
    boolean updateMagazine(int isbn, String newTitle, int newYear, int newPages, Periodicity newPeriodicity);

    void printAll();
}
