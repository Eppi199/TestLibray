package ru.training.webapp.MyLibrray.model;

import javax.persistence.*;

@Entity
@Table(name = "books_authors")
public class Book_Author {

    @Id
    @Column(name = "id_all")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_all;

    @Column(name = "id_book")

    private int id_book;

    @Column(name = "id_author")
    private int id_author;

    @Column(name = "title_book")
    private String title_book;

    @Column(name = "name_author")
    private String name_author;

    @Column(name = "year_book")
    private String year_book;

    public int getId_all() {
        return id_all;
    }
    public void setId_all(int id_all) {
        this.id_all = id_all;
    }

    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_author() {
        return id_author;
    }
    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getTitle_book() {
        return title_book;
    }
    public void setTitle_book(String title_book) {
        this.title_book = title_book;
    }

    public String getName_author() {
        return name_author;
    }
    public void setName_author(String name_author) { this.name_author = name_author; }

    public String getYear_book() {
        return year_book;
    }
    public void setYear_book(String year_book) {
        this.year_book = year_book;
    }

    @Override
    public String toString() {
        return "Books_Authors{" +
                "id_all=" + id_all +
                ", id_book=" + id_book + '\'' +
                ", id_author=" + id_author + '\'' +
                ", title_book=" + title_book + '\'' +
                ", name_author=" + name_author + '\'' +
                ", year_book=" + year_book + '\'' +
                '}';
    }
}

