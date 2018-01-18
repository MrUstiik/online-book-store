package com.example.controllers;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.service.interfaces.AddressServiceInterface;
import com.example.service.interfaces.SecurityServiceInterface;
import com.example.service.interfaces.UserServiceInterface;
import com.example.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LoginController {

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private SecurityServiceInterface securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AddressServiceInterface addressService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        user.setAddress(new Address());
        model.addAttribute("userForm", user);
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        addressService.addAddress(userForm.getAddress());
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }
}