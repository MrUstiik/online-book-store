package com.example.controllers;

import com.example.service.interfaces.AddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class AddressController {
    @Autowired
    private AddressServiceInterface addressServiceInterface;
}
