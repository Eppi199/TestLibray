package ru.training.webapp.MyLibrray.dao;

import org.hibernate.Query;
import ru.training.webapp.MyLibrray.model.Book;
import ru.training.webapp.MyLibrray.model.Author;
import ru.training.webapp.MyLibrray.model.Book_Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private final static Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book, Author author) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);

        String nameAuthor = author.getName_author();
        Query query = session.createQuery("from Author where name_author = :nameAuthor");
        query.setString("nameAuthor", nameAuthor);
        List<Author> check_auth  = query.list(); // получаем id автора по введенному имени

        if (check_auth.size() <= 0) {
            session.persist(author);    // если автора нет, то добавляем
        }
        logger.info("Book successfully saved. Book details: " + book + author);
    }

    @Override
    public void addBook_Author(Book_Author book_author) {
        Session session = this.sessionFactory.getCurrentSession();

        String bkauAuthor = book_author.getName_author();
        Query query = session.createQuery("from Author where name_author = :bkauAuthor");
        query.setString("bkauAuthor", bkauAuthor);
        List<Author> check_BookAuth  = query.list(); // получаем id автора по введенному имени

        if (check_BookAuth.size() <= 0) {
            session.persist(book_author); // если автора нет, то добавляем
        } else {
            book_author.setId_author(check_BookAuth.get(0).getId_author()); //получаю ID уже имеющегося автора и записываю это
            session.persist(book_author); // если автор есть, то передаем существующий id и НЕ создаем дубликат
        }
        logger.info("Book successfully saved. Book details: " + book_author);
    }


    @Override
    public void updateBook(Book_Author book_author, Book book, Author author) {
        Session session = this.sessionFactory.getCurrentSession();
        Book_Author new_book_author = (Book_Author) session.load(Book_Author.class, new Integer(book_author.getId_all()));
        Book new_book = (Book) session.load(Book.class, new Integer(book.getId_book()));
        Author new_author = (Author) session.load(Author.class, new Integer(author.getId_author()));

        session.update(new_book_author);
        session.update(new_book);
        session.update(new_author);
        logger.info("Book successfully update. Book details: " + book + author + book_author);
    }

    @Override
    public void removeBook(int idAll, int id, int authId) {
        Session session = this.sessionFactory.getCurrentSession();
        Book_Author book_author = (Book_Author) session.load(Book_Author.class, new Integer(idAll));
        Book book = (Book) session.load(Book.class, new Integer(id));
        Author author = (Author) session.load(Author.class, new Integer(authId));

        Query query = session.createQuery("from Book_Author where id_author = :id_author");
        query.setLong("id_author", authId);
        List<Book_Author> count_authId  = query.list(); // Получаем весь список авторов по заданному id

        if ((book_author != null)) {
            session.delete(book_author);
            session.delete(book);
            if(count_authId.size() <= 1) { //если у нас больше одной записи автора в таблице ракурса, то не удаляем из основной таблицы
                session.delete(author); //как только в таблице ракурса остался один автор без дубликатов, то чистим везде
            }
        }
        logger.info("Book successfully removed. Book details: " + book + author);
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    public Author getAuthorById(int authId) {
        Session session = this.sessionFactory.getCurrentSession();
        Author author = (Author) session.load(Author.class, new Integer(authId));
        logger.info("Author successfully loaded. Author details: " + author);

        return author;
    }

    @Override
    public Book_Author getBook_AuthorById(int idAll) {
        Session session = this.sessionFactory.getCurrentSession();
        Book_Author book_author = (Book_Author) session.load(Book_Author.class, new Integer(idAll));
        logger.info("Author successfully loaded. Author details: " + book_author);
        return book_author;
    }

    @Override
    public Author getAuthorByName(String nameAuth) {
        Session session = this.sessionFactory.getCurrentSession();
        Author author = (Author) session.load(Author.class, new String(nameAuth));
        logger.info("Author successfully loaded. Author details: " + author);

        return author;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBook() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();

        for (Book book : bookList) {
            logger.info("Book list: " + book);
        }
        return bookList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Author> listAuthor() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Author> authorList = session.createQuery("from Author").list();

        for (Author author : authorList) {
            logger.info("Author list: " + author);
        }
        return authorList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book_Author> listBook_Author() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book_Author> book_authorsList = session.createQuery("from Book_Author").list();
        for (Book_Author book_author : book_authorsList) {
            logger.info("Author list: " + book_author);
        }
        return book_authorsList;
    }
}
