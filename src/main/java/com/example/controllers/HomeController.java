package com.example.controllers;

import com.example.service.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private BookServiceInterface bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexUser(Model model) {
        model.addAttribute("books", bookService.getMostPopular(6));
        return "index";
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", bookService.getMostPopular(6));
        return "/admin/index";
    }
}