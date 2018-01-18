package com.example.controllers;


import com.example.entity.Author;
import com.example.entity.Book;
import com.example.service.interfaces.AuthorServiceInterface;
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
import java.util.Comparator;
import java.util.Set;

@Controller
public class AuthorController {
    @Autowired
    private AuthorServiceInterface authorService;


    @RequestMapping(value = "/admin/author", method = RequestMethod.GET)
    public String allAuthors(Model model){
        model.addAttribute("authors", authorService.getAll());
        return "/admin/author";
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String allAuthorsUser(Model model){
        model.addAttribute("authors", authorService.getAll());
        return "/author";
    }

    @RequestMapping(value = "/admin/delete/author", method = RequestMethod.GET, params = {"authorId"})
    public String deleteAuthor(@RequestParam int authorId, Model model){
        File file = new File("src/main/resources/public/img/author/"
                + authorId + ".jpg");
        file.delete();
        //Files.deleteIfExists("src/main/resources/public/img/author/"+ authorId + ".jpg");
        authorService.deleteAuthor(authorService.getById(authorId));
        return "redirect:/admin/author";
    }

    @RequestMapping(value = "/admin/edit/author", method = RequestMethod.GET, params = {"authorId"})
    public String editAuthor(@RequestParam int authorId, Model model){
        model.addAttribute("author", authorService.getById(authorId));
        return "/admin/edit/author";
    }

    @RequestMapping(value = "/admin/add/author", method = RequestMethod.GET)
    public String addAuthor(Model model){
        model.addAttribute("author", new Author());
        return "/admin/add/author";
    }

    @RequestMapping(value = "/admin/add/author", method = RequestMethod.POST)
    public String addAuthor(@Valid Author author, @RequestParam(value = "image") MultipartFile image, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "error";
        }
        author.setBooks(authorService.getById(author.getAuthorId()).getBooks());
        authorService.addAuthor(author);
        if(!image.isEmpty()){
            try{
                if (!image.getContentType().equals("image/jpeg")) {
                    throw new RuntimeException("Only JPG images are accepted");
                }
                File file = new File("src/main/resources/public/img/author/"
                        + author.getAuthorId() + ".jpg");
                FileUtils.writeByteArrayToFile(file, image.getBytes());
            }catch (RuntimeException re) {
                bindingResult.addError(new ObjectError("image", "Only jpg images"));
                return "error";
            }catch (IOException e) {
                bindingResult.reject(e.getMessage());
                return "error";
            }
        }
        return "redirect:/admin/author";
    }

    @RequestMapping(value = "/details/author", method = RequestMethod.GET)
    public String getAuthor(@RequestParam int authorId, Model model){
        Author author = authorService.getById(authorId);
        Set<Book> books = author.getBooks();
        books.stream().sorted(Comparator.comparing(Book::getYearOfPublication).reversed()).close();
        author.setBooks(books);
        model.addAttribute("author", author);
        return "/details/author";
    }
}