package com.vaidik.addressbook.storage;

import com.vaidik.addressbook.model.Contact;
import com.vaidik.addressbook.util.JSONUtil;

import java.util.List;

public class JSONStorage implements ContactStorage {

    @Override
    public void save(String filePath, List<Contact> contacts) {
        JSONUtil.writeContactsToJSON(filePath, contacts);
    }

    @Override
    public List<Contact> load(String filePath) {
        return JSONUtil.readContactsFromJSON(filePath);
    }
}
