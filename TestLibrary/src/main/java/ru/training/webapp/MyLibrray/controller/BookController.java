package ru.training.webapp.MyLibrray.controller;

import ru.training.webapp.MyLibrray.model.Author;
import ru.training.webapp.MyLibrray.model.Book;
import ru.training.webapp.MyLibrray.model.Book_Author;
import ru.training.webapp.MyLibrray.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    private void setBookService(BookService bookService)
    {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("author", new Author());
        model.addAttribute("book_author", new Book_Author());
        model.addAttribute("listBooks", this.bookService.listBook());
        model.addAttribute("listAuthors", this.bookService.listAuthor());
        model.addAttribute("listBook_Author", this.bookService.listBook_Author());
        return  "books";
    }


    @RequestMapping(value = "books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book_author") Book_Author book_author, @ModelAttribute("book") Book book, @ModelAttribute("author") Author author){

        if((book.getId_book() == 0) && (author.getId_author() == 0)){

            this.bookService.addBook(book, author);

            book_author.setId_book(book.getId_book());
            book_author.setId_author(author.getId_author());
            book_author.setTitle_book(book.getTitle_book());
            book_author.setName_author(author.getName_author());
            book_author.setYear_book(book.getYear_book());

            this.bookService.addBook_Author(book_author);
        }else{
            this.bookService.updateBook(book_author, book, author);
        }
        return  "redirect:/books";
    }

    @RequestMapping(value = "remove/{idAll}/{id}/{authId}")
    public String removeBook(@PathVariable("idAll") int idAll, @PathVariable("id") int id, @PathVariable("authId") int authId){
        this.bookService.removeBook(idAll, id, authId);
        return  "redirect:/books";
    }

    @RequestMapping("edit/{idAll}/{id}/{authId}")
    public String editBook(@PathVariable("idAll") int idAll, @PathVariable("id") int id, @PathVariable("authId") int authId, Model model){
        model.addAttribute("book_author", this.bookService.getBook_AuthorById(idAll));
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("author", this.bookService.getAuthorById(authId));
        model.addAttribute("listBook_Author", this.bookService.listBook_Author());
        model.addAttribute("listBooks", this.bookService.listBook());
        model.addAttribute("listAuthors", this.bookService.listAuthor());
        return "books";
    }
}
