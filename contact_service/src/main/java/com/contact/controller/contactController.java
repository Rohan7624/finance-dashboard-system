package com.contact.controller;


import com.contact.entity.contact;
import com.contact.service.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class contactController {

   @Autowired
    private contactService contactService;


    @RequestMapping("/user/{userId}")
    public List<contact> getContacts(@PathVariable("userId") Long userId){

        return this.contactService.getContactsOfUser(userId);
    }
}
