package com.vaidik.addressbook.storage;

import com.vaidik.addressbook.model.Contact;
import java.util.List;

public interface ContactStorage {

    void save(String filePath, List<Contact> contacts);

    List<Contact> load(String filePath);
}
