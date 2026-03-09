package com.vaidik.addressbook.threads;

import com.vaidik.addressbook.model.Contact;
import com.vaidik.addressbook.repository.ContactRepository;

public class AddContactTask implements Runnable {

    private final ContactRepository repository;
    private final Contact contact;

    public AddContactTask(ContactRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact;
    }

    @Override
    public void run() {
        repository.addContact(contact);
        System.out.println("Inserted contact: " + contact.getFirstName());
    }
}
