package ru.training.webapp.MyLibrray.service;

import ru.training.webapp.MyLibrray.dao.BookDao;
import ru.training.webapp.MyLibrray.model.Author;
import ru.training.webapp.MyLibrray.model.Book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.training.webapp.MyLibrray.model.Book_Author;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book, Author author) {
        this.bookDao.addBook(book, author);
    }

    @Override
    @Transactional
    public void addBook_Author(Book_Author book_author) {
        this.bookDao.addBook_Author(book_author);
    }

    @Override
    @Transactional
    public void updateBook(Book_Author book_author, Book book, Author author) {
        this.bookDao.updateBook(book_author, book, author);
    }

    @Override
    @Transactional
    public void removeBook(int idAll, int id, int authId) {
        this.bookDao.removeBook(idAll, id, authId);
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public Author getAuthorById(int authId) {
        return this.bookDao.getAuthorById(authId);
    }

    @Override
    @Transactional
    public Book_Author getBook_AuthorById(int idAll) {  return this.bookDao.getBook_AuthorById(idAll);   }

    @Override
    @Transactional
    public Author getAuthorByName(String nameAuth) {
        return this.bookDao.getAuthorByName(nameAuth);
    }

    @Override
    @Transactional
    public List<Book> listBook() {
        return this.bookDao.listBook();
    }

    @Override
    @Transactional
    public List<Author> listAuthor() {
        return this.bookDao.listAuthor();
    }

    @Override
    @Transactional
    public List<Book_Author> listBook_Author() {
        return this.bookDao.listBook_Author();
    }
}
