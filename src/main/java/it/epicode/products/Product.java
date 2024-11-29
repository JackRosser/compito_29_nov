package it.epicode.products;

public abstract class Product {
    private int isbn;
    private String title;
    private int year;
    private int pages;

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Product(int isbn, String title, int year, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Product{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}

