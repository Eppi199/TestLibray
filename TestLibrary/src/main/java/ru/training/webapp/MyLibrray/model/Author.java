package ru.training.webapp.MyLibrray.model;

import javax.persistence.*;

@Entity
@Table(name = "authors")

public class Author {
    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_author;

    @Column(name = "name_author")
    private String name_author;

    public int getId_author() {  return id_author;   }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName_author() {
        return name_author;
    }
    public void setName_author(String name_author) { this.name_author = name_author; }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", name_author=" + name_author + '\'' +
                '}';
    }
}
