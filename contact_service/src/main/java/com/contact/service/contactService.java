package com.contact.service;

import com.contact.entity.contact;

import java.util.List;

public interface contactService {

    public List<contact> getContactsOfUser(Long userId);
}
