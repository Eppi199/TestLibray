package ru.training.webapp.MyLibrray.model;

import javax.persistence.*;

@Entity
@Table(name = "books")

public class Book {
    @Id
    @Column(name = "id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_book;

    @Column(name = "title_book")
    private String title_book;

    @Column(name = "year_book")
    private String year_book;

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitle_book() {
        return title_book;
    }

    public void setTitle_book(String title_book) {
        this.title_book = title_book;
    }

    public String getYear_book() {
        return year_book;
    }

    public void setYear_book(String year_book) {
        this.year_book = year_book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + id_book +
                ", title_book=" + title_book + '\'' +
                ", year_book=" + year_book + '\'' +
                '}';
    }
}
