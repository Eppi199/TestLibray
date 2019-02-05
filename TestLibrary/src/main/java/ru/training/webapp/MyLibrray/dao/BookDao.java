package ru.training.webapp.MyLibrray.dao;

import ru.training.webapp.MyLibrray.model.Author;
import ru.training.webapp.MyLibrray.model.Book;
import ru.training.webapp.MyLibrray.model.Book_Author;

import java.util.List;

public interface BookDao {
    public void addBook(Book book, Author author); //Добавляем книги и авторов
    public void addBook_Author( Book_Author book_author); //Добавляем данные в таблицу ракурсов

    public void updateBook(Book_Author book_author, Book book, Author author); //Обновляем данные

    public void removeBook(int idAll, int id, int authId); //Удаляем

    public Book getBookById(int id); //Ну тут понятно
    public Author getAuthorById(int authId); //И тут тоже
    public Book_Author getBook_AuthorById(int idAll);
    public Author getAuthorByName(String nameAuth); //Берем имя автора

    public List<Book> listBook();
    public List<Author> listAuthor();
    public List<Book_Author> listBook_Author();
}