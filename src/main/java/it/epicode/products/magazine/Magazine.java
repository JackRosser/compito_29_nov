package it.epicode.products.magazine;

import it.epicode.products.Product;

public class Magazine extends Product {
private Periodicity periodicity;

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public Magazine(int isbn, String title, int year, int pages, Periodicity periodicity) {
        super(isbn, title, year, pages);
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "ISBN=" + getIsbn() +
                ", Title='" + getTitle() + '\'' +
                ", Year=" + getYear() +
                ", Pages=" + getPages() +
                ", Periodicity=" + periodicity +
                '}';
    }

}
