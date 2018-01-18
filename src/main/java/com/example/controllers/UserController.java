package com.example.controllers;

import com.example.entity.Order;
import com.example.entity.User;
import com.example.service.interfaces.OrderServiceInterface;
import com.example.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private OrderServiceInterface orderService;

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String allUser(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/user";
    }

    @RequestMapping(value = "/admin/edit/user", method = RequestMethod.GET, params = {"userId"})
    public String getUserEdit(@RequestParam int userId, Model model) {
        model.addAttribute("user", userService.getById(userId));
        return "/admin/edit/user";
    }

    @RequestMapping(value = "/admin/edit/user", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/user";
        }
        userService.editUser(user);
       // addressService.editAddress(user.getAddress());
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/delete/user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam int userId, Model model) {
        userService.deleteUser(userService.getById(userId));
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserDetails(Model model) {
        User user = userService.getByLogin(getPrincipal());
        model.addAttribute("user", user);
        List<Order> orders = orderService.getByUser(user);
        orders.sort(Comparator.comparing(Order::getDate).reversed());
        model.addAttribute("orders", orders);
        return "/user";
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
