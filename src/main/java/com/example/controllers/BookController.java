package com.example.controllers;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Genre;
import com.example.service.interfaces.AuthorServiceInterface;
import com.example.service.interfaces.BookServiceInterface;
import com.example.service.interfaces.GenreServiceInterface;
import com.example.service.interfaces.PublisherServiceInterface;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BookController {
    @Autowired
    private BookServiceInterface bookService;

    @Autowired
    private AuthorServiceInterface authorService;

    @Autowired
    private GenreServiceInterface genreService;

    @Autowired
    private PublisherServiceInterface publisherService;

    @RequestMapping(value = "/admin/book", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("books", bookService.getAll());
        return "/admin/book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String allBooksUser(Model model){
        model.addAttribute("books", bookService.getAll());
        return "/book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET, params = {"bookTitle"})
    public String searchBook(@RequestParam String bookTitle, Model model){
        model.addAttribute("books", bookService.getByTitle(bookTitle));
        return "/book";
    }

    @RequestMapping(value = "/admin/delete/book", method = RequestMethod.GET, params = {"bookId"})
    public String deleteBook(@RequestParam int bookId, Model model){
        File file = new File("src/main/resources/public/img/book/"
                + bookId + ".jpg");
        file.delete();
        //Files.deleteIfExists("src/main/resources/public/img/book/"+ bookId + ".jpg");
        bookService.deleteBook(bookService.getById(bookId));
        return "redirect:/admin/book";
    }

    @RequestMapping(value = "/admin/edit/book", method = RequestMethod.GET, params = {"bookId"})
    public String editBook(@RequestParam int bookId, Model model){
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("book", bookService.getById(bookId));
        return "/admin/edit/book";
    }

    @RequestMapping(value="/admin/add/book", method = RequestMethod.GET)
    public String addBook(Model model){
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("book", new Book());
        return "/admin/add/book";
    }

    @RequestMapping(value="/admin/add/book", method = RequestMethod.POST)
    public String addBook(@Valid Book book, @RequestParam(value = "image") MultipartFile image, BindingResult bindingResult,  Model model){
        if(bindingResult.hasErrors()){
            return "error";
        }
        book.setPublisher(publisherService.getByTitle(book.getPublisherTitle()));
        Book oldBook = bookService.getById(book.getBookId());
        if (oldBook != null) {
            book.setAuthors(oldBook.getAuthors());
            book.setGenres(oldBook.getGenres());
            book.setOrders(oldBook.getOrders());
        }
        bookService.addBook(book);
        publisherService.editPublisher(book.getPublisher());
        if(!image.isEmpty()){
            try{
                if (!image.getContentType().equals("image/jpeg")) {
                    throw new RuntimeException("Only JPG images are accepted");
                }
                File file = new File("src/main/resources/public/img/book/"
                        + book.getBookId() + ".jpg");

                FileUtils.writeByteArrayToFile(file, image.getBytes());
            }catch (RuntimeException re) {
                bindingResult.addError(new ObjectError("image", "Only jpg images"));
                return "error";
            }catch (IOException e) {
                bindingResult.reject(e.getMessage());
                return "error";
            }
        }
        return "redirect:/admin/book";
    }

    @RequestMapping(value = "/admin/add/genre_to_book", method = RequestMethod.GET, params = {"bookId"})
    public String addGenres(@RequestParam int bookId, Model model){
        Book book = bookService.getById(bookId);
        if(book.getGenreIds() == null){
            book.setGenreIds(new ArrayList<>());
        }
        model.addAttribute("book", book);
        model.addAttribute("genres", genreService.getAll());
        return "/admin/add/genre_to_book";
    }

    @RequestMapping(value = "/admin/add/genre_to_book", method = RequestMethod.POST)
    public String addGenres(@Valid Book book, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "error";
        }
        Book old = bookService.getById(book.getBookId());
        if (book.getOrders() == null) {
            book.setOrders(old.getOrders());
        }
        if (book.getAuthors() == null) {
            book.setAuthors(old.getAuthors());
        }
        if (book.getPublisher() == null) {
            book.setPublisher(old.getPublisher());
        }
        Set<Genre> newGenres = new HashSet<>();
        for(int genreId : book.getGenreIds()){
            newGenres.add(genreService.getById(genreId));
        }
        book.setGenres(newGenres);
        bookService.editBook(book);
        return "redirect:/admin/book";
    }

    @RequestMapping(value = "/details/book", method = RequestMethod.GET, params = {"bookId"}) //params??
    public String getBook(@RequestParam int bookId, Model model){
        model.addAttribute("book", bookService.getById(bookId));
        return "/details/book";
    }

    @RequestMapping(value = "admin/add/author_to_book", method = RequestMethod.GET, params = {"bookId"})
    public String addAuthors(@RequestParam int bookId, Model model){
        Book book = bookService.getById(bookId);
        if(book.getAuthorIds() == null){
            book.setAuthorIds(new ArrayList<>());
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        return "admin/add/author_to_book";
    }

    @RequestMapping(value = "admin/add/author_to_book", method = RequestMethod.POST)
    public String addAuthors(@Valid Book book, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "error";
        }
        Book old = bookService.getById(book.getBookId());
        if (book.getOrders() == null) {
            book.setOrders(old.getOrders());
        }
        if (book.getGenres() == null) {
            book.setGenres(old.getGenres());
        }
        if (book.getPublisher() == null) {
            book.setPublisher(old.getPublisher());
        }
        Set<Author> newAuthors = new HashSet<>();
        for(int authorId : book.getAuthorIds()){
            newAuthors.add(authorService.getById(authorId));
        }
        book.setAuthors(newAuthors);
        bookService.editBook(book);
        return "redirect:/admin/book";
    }

}
