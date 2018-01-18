package com.example.controllers;

import com.example.entity.Genre;
import com.example.service.interfaces.GenreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class GenreController {
    @Autowired
    private GenreServiceInterface genreService;

    @RequestMapping(value = "/admin/genre", method = RequestMethod.GET)
    public String allGenre(Model model) {
        List<Genre> genres = genreService.getAll();
        genres.sort(Comparator.comparingInt(o -> o.getBooks().size()));
        model.addAttribute("genres", genres);
        return "/admin/genre";
    }

    @RequestMapping(value = "/genre", method = RequestMethod.GET)
    public String allGenreUser(Model model) {
        List<Genre> genres = genreService.getAll();
        genres.sort(Comparator.comparingInt(o -> o.getBooks().size()));
        Collections.reverse(genres);
        model.addAttribute("genres", genres);
        return "/genre";
    }

    @RequestMapping(value = "/admin/delete/genre", method = RequestMethod.GET)
    public String deleteGenre(@RequestParam int genreId, Model model) {
        genreService.deleteGenre(genreService.getById(genreId));
        return "redirect:/admin/genre";
    }

    @RequestMapping(value = "/admin/edit/genre", method = RequestMethod.GET, params = {"genreId"})
    public String editGenre(@RequestParam int genreId, Model model) {
        model.addAttribute("genre", genreService.getById(genreId));
        return "/admin/edit/genre";
    }

    @RequestMapping(value = "/admin/add/genre", method = RequestMethod.GET)
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "/admin/add/genre";
    }

    @RequestMapping(value = "/admin/add/genre", method = RequestMethod.POST)
    public String addGenre(@Valid Genre genre, BindingResult bindingResult, Model model) {
        genreService.addGenre(genre);
        return "redirect:/admin/genre";
    }

    @RequestMapping(value = "/details/genre", method = RequestMethod.GET, params = {"genreId"})
    public String getGenre(@RequestParam int genreId, Model model){
        model.addAttribute("genre", genreService.getById(genreId));
        return "/details/genre";
    }

}
