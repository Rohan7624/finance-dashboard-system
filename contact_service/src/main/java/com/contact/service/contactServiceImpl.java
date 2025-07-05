package com.contact.service;

import com.contact.entity.contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class contactServiceImpl implements contactService{

    //Fake list of contact

    List<contact> list = List.of(
            new contact(1L,"jagadish1@gmail.com","Jagadish Pradhan",1311L),
            new contact(2L,"sumi2@gmail.com","Sumi Ojha",1312L),
            new contact(3L,"rohan3@gmail.com","Mr. Rohan",1313L),
            new contact(4L,"Archana4@gmail.com","Archana Ojha",1311L)
    );

    @Override
    public List<contact> getContactsOfUser(Long userId) {
        return list.stream().filter(contact -> contact.getUserId()
                .equals(userId)).collect(Collectors.toList());
    }
}
