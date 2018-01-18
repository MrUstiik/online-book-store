package com.example.controllers;

import com.example.entity.Address;
import com.example.entity.Publisher;
import com.example.service.interfaces.AddressServiceInterface;
import com.example.service.interfaces.PublisherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PublisherController {
    @Autowired
    private PublisherServiceInterface publisherService;

    @Autowired
    private AddressServiceInterface addressService;


    @RequestMapping(value = "/admin/publisher", method = RequestMethod.GET)
    public String allPublishers(Model model){
        model.addAttribute("publishers", publisherService.getAll());
        return "/admin/publisher";
    }

    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public String allPublishersUser(Model model){
        model.addAttribute("publishers", publisherService.getAll());
        return "/publisher";
    }

    @RequestMapping(value = "/admin/delete/publisher", method = RequestMethod.GET, params = {"publisherId"})
    public String deletePublisher(@RequestParam int publisherId){
        publisherService.deletePublisher(publisherService.getById(publisherId));
        return "redirect:/admin/publisher";
    }

    @RequestMapping(value = "/admin/edit/publisher", method = RequestMethod.GET, params = {"publisherId"})
    public String editPublisher(@RequestParam int publisherId, Model model){
        model.addAttribute("publisher", publisherService.getById(publisherId));
        return "/admin/edit/publisher";
    }

    @RequestMapping(value = "/admin/add/publisher", method = RequestMethod.GET)
    public String addPublisher(Model model){
        Publisher publisher = new Publisher();
        publisher.setAddress(new Address());
        model.addAttribute("publisher", publisher);
        return "/admin/add/publisher";
    }

    @RequestMapping(value = "/admin/add/publisher", method = RequestMethod.POST)
    public String addPublisher(@Valid Publisher publisher, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "error";
        }
        addressService.addAddress(publisher.getAddress());
        publisherService.addPublisher(publisher);
        return "redirect:/admin/publisher";
    }

    @RequestMapping(value = "/details/publisher", method = RequestMethod.GET, params = {"publisherId"})
    public String getPublisher(@RequestParam int publisherId, Model model){
        model.addAttribute("publisher", publisherService.getById(publisherId));
        return "/details/publisher";
    }
}
