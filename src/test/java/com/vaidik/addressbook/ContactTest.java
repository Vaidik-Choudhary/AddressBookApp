package com.vaidik.addressbook;

import org.junit.jupiter.api.Test;
import com.vaidik.addressbook.model.Contact;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void givenValidContactDetails_whenObjectCreated_shouldReturnCorrectValues() {

        Contact contact = new Contact(
                "Vaidik",
                "Choudhary",
                "Arera Colony",
                "Bhopal",
                "MP",
                "462001",
                "919876543210",
                "vaidik@example.com"
        );

        assertEquals("Vaidik", contact.getFirstName());
        assertEquals("Choudhary", contact.getLastName());
        assertEquals("Bhopal", contact.getCity());
        assertEquals("MP", contact.getState());
        assertEquals("919876543210", contact.getPhoneNumber());
    }


    @Test
    public void givenEmptyConstructor_whenSettersUsed_shouldReturnCorrectValues() {

        Contact contact = new Contact();

        contact.setFirstName("Vaidik");
        contact.setLastName("Choudhary");
        contact.setCity("Bhopal");

        assertEquals("Vaidik", contact.getFirstName());
        assertEquals("Choudhary", contact.getLastName());
        assertEquals("Bhopal", contact.getCity());
    }


    @Test
    public void givenTwoContactsWithSameName_whenCompared_shouldReturnEqual() {

        Contact c1 = new Contact(
                "Vaidik", "Choudhary",
                "Addr1", "City1", "State1",
                "111111", "9999999999", "a@mail.com"
        );

        Contact c2 = new Contact(
                "Vaidik", "Choudhary",
                "Addr2", "City2", "State2",
                "222222", "8888888888", "b@mail.com"
        );

        assertEquals(c1, c2);
    }


    @Test
    public void givenTwoContactsWithDifferentNames_whenCompared_shouldReturnNotEqual() {

        Contact c1 = new Contact("Vaidik", "Choudhary", "", "", "", "", "", "");
        Contact c2 = new Contact("Rahul", "Verma", "", "", "", "", "", "");

        assertNotEquals(c1, c2);
    }


    @Test
    public void givenSameContactObjects_whenHashCodeCalled_shouldReturnSameHash() {

        Contact c1 = new Contact("Vaidik", "Choudhary", "", "", "", "", "", "");
        Contact c2 = new Contact("Vaidik", "Choudhary", "", "", "", "", "", "");

        assertEquals(c1.hashCode(), c2.hashCode());
    }


    @Test
    public void givenContactWithNullValues_shouldHandleGracefully() {

        Contact contact = new Contact();

        assertNull(contact.getFirstName());
        assertNull(contact.getLastName());
        assertNull(contact.getCity());
    }


    @Test
    public void givenContact_whenUpdatingPhoneNumber_shouldReturnUpdatedValue() {

        Contact contact = new Contact();

        contact.setPhoneNumber("1111111111");
        contact.setPhoneNumber("9999999999");

        assertEquals("9999999999", contact.getPhoneNumber());
    }


    @Test
    public void givenContact_whenUpdatingEmail_shouldReturnUpdatedEmail() {

        Contact contact = new Contact();

        contact.setEmail("old@mail.com");
        contact.setEmail("new@mail.com");

        assertEquals("new@mail.com", contact.getEmail());
    }

}
