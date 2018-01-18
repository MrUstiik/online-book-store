package com.example.controllers;

import com.example.entity.Order;
import com.example.entity.OrderBook;
import com.example.entity.User;
import com.example.service.interfaces.BookServiceInterface;
import com.example.service.interfaces.OrderBookServiceInterface;
import com.example.service.interfaces.OrderServiceInterface;
import com.example.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;

@Controller
public class OrderController {
    @Autowired
    private OrderServiceInterface orderService;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private BookServiceInterface bookService;

    @Autowired
    private OrderBookServiceInterface orderBookService;

    @RequestMapping(value = "/details/order", method = RequestMethod.GET, params = {"orderId"})
    public String booksOfOrder(@RequestParam int orderId, Model model) {
        User user = userService.getByLogin(getPrincipal());
        if (user == null) {
            return "redirect:/";
        }
        Order order = orderService.getById(orderId);
//        List<Book> books = new ArrayList<>();
//        if (order.getUser().equals(user)) {
//            for(OrderBook ob : order.getBooks()){
//                books.add(bookService.getById(ob.getBookId()));
//            }
//        }
//        model.addAttribute("books", books);
        model.addAttribute("order", order);
        model.addAttribute("orderBooks", order.getBooks());
        return "/details/order";
    }

    @RequestMapping(value = "/add_book_to_order", method = RequestMethod.GET, params = {"bookId"})
    public String addBooksToOrder(@RequestParam int bookId, Model model) {
        User user = userService.getByLogin(getPrincipal());
        if (user == null) {
            return "redirect:/";
        }
        Order order = user.getOrders().stream().max(Comparator.comparing(Order::getDate)).get();
        if (order.getBooks().contains(orderBookService.getByOrderIdAndBookId(order.getOrderId(), bookId))) {
            for (OrderBook ob : order.getBooks()) {
                if (ob.getBookId() == bookId) {
                    ob.setQuantityOfBooks(ob.getQuantityOfBooks() + 1);
                    break;
                }
            }
        } else {
            OrderBook ob = new OrderBook();
            ob.setOrderId(order.getOrderId());
            ob.setOrder(order);
            ob.setBookId(bookId);
            ob.setBook(bookService.getById(bookId));
            ob.setQuantityOfBooks(1);
            order.getBooks().add(ob);
        }
//        order.setBooks(order.getBooks());
//        order.getBooks().
        orderService.editOrder(order);
        return "redirect:/details/order?orderId=" + order.getOrderId();
    }

    @RequestMapping(value = "/delete_book_from_order", method = RequestMethod.GET, params = {"bookId"})
    public String deleteBookFromOrder(@RequestParam int bookId, Model model) {
        User user = userService.getByLogin(getPrincipal());
        if (user == null) {
            return "redirect:/";
        }
        Order order = user.getOrders().stream().max(Comparator.comparing(Order::getDate)).get();
        OrderBook ob = orderBookService.getByOrderIdAndBookId(order.getOrderId(), bookId);
        if (ob.getQuantityOfBooks() > 1) {
            ob.setQuantityOfBooks(ob.getQuantityOfBooks() - 1);
        } else {
            order.getBooks().remove(ob);
        }
        orderService.editOrder(order);
        return "redirect:/details/order?orderId=" + order.getOrderId();
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
