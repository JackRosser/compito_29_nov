package it.epicode.products.book;

import it.epicode.products.Product;

public class Book extends Product {
private String author;
private Genre genre;

public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book(int isbn, String title, int year, int pages, String author, Genre genre) {
        super(isbn, title, year, pages);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + getIsbn() +
                ", Title='" + getTitle() + '\'' +
                ", Year=" + getYear() +
                ", Pages=" + getPages() +
                ", Author='" + author + '\'' +
                ", Genre=" + genre +
                '}';
    }

}
