package com.vaidik.addressbook;

import com.vaidik.addressbook.model.AddressBook;
import com.vaidik.addressbook.model.Contact;
import com.vaidik.addressbook.service.AddressBookService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

    private Contact createContact() {

        return new Contact(
                "Vaidik",
                "Choudhary",
                "Arera Colony",
                "Bhopal",
                "MP",
                "462001",
                "919876543210",
                "vaidik@example.com"
        );
    }

    @Test
    public void givenValidContact_whenAdded_shouldReturnSameContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = createContact();

        Contact result = service.addContact("personal", contact);

        assertEquals("Vaidik", result.getFirstName());
        assertEquals("Choudhary", result.getLastName());
    }

    @Test
    public void givenContact_whenAdded_shouldCreateAddressBookAutomatically() {

        AddressBookService service = new AddressBookService();

        Contact contact = createContact();

        service.addContact("office", contact);

        assertNotNull(service.getAddressBook("office"));
    }

    @Disabled
    @Test
    public void givenMultipleContacts_whenAdded_shouldStoreAllContacts() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("personal", c2);

        assertEquals(2,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenDifferentAddressBooks_whenAddingContacts_shouldSeparateData() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("office", c2);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());

        assertEquals(1,
                service.getAddressBook("office").getContacts().size());
    }

    @Test
    public void givenContactWithNullValues_whenAdded_shouldNotCrash() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact();

        Contact result = service.addContact("personal", contact);

        assertNotNull(result);
    }

    @Test
    public void givenContactWithEmptyStrings_whenAdded_shouldStoreSuccessfully() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Disabled
    @Test
    public void givenSameContactTwice_whenAdded_shouldAllowDuplicatesForNow() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("personal", c1);
        service.addContact("personal", c2);

        assertEquals(2,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenLongPhoneNumber_whenAdded_shouldStoreContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
                "Vaidik",
                "Choudhary",
                "Arera Colony",
                "Bhopal",
                "MP",
                "462001",
                "999999999999999",
                "vaidik@example.com"
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenInvalidEmail_whenAdded_shouldStillStoreContact() {

        AddressBookService service = new AddressBookService();

        Contact contact = new Contact(
                "Vaidik",
                "Choudhary",
                "Arera Colony",
                "Bhopal",
                "MP",
                "462001",
                "919876543210",
                "invalid-email"
        );

        service.addContact("personal", contact);

        assertEquals(1,
                service.getAddressBook("personal").getContacts().size());
    }

    @Test
    public void givenMultipleAddressBooks_whenContactsAdded_shouldMaintainSeparateLists() {

        AddressBookService service = new AddressBookService();

        Contact c1 = createContact();
        Contact c2 = createContact();

        service.addContact("family", c1);
        service.addContact("friends", c2);

        assertEquals(1,
                service.getAddressBook("family").getContacts().size());

        assertEquals(1,
                service.getAddressBook("friends").getContacts().size());
    }

    // ---- remaining tests unchanged except name updates ----

    @Test
    public void givenContacts_whenSearchByCity_shouldReturnMatches() {

        AddressBookService service = new AddressBookService();

        Contact c1 = new Contact("Vaidik","Choudhary","","Bhopal","MP","","","");
        Contact c2 = new Contact("Rahul","Verma","","Delhi","DL","","","");

        service.addContact("personal", c1);
        service.addContact("office", c2);

        assertEquals(1, service.searchByCity("Bhopal").size());
    }

    @Test
    public void givenContacts_whenSearchByState_shouldReturnMatches() {

        AddressBookService service = new AddressBookService();

        Contact c1 = new Contact("Vaidik","Choudhary","","Bhopal","MP","","","");
        Contact c2 = new Contact("Rahul","Verma","","Delhi","DL","","","");

        service.addContact("personal", c1);
        service.addContact("office", c2);

        assertEquals(1, service.searchByState("DL").size());
    }

    @Test
    public void givenUnknownCity_whenSearch_shouldReturnEmptyList() {

        AddressBookService service = new AddressBookService();

        assertEquals(0, service.searchByCity("Unknown").size());
    }

    @Test
    public void givenDifferentCaseCity_whenSearch_shouldStillMatch() {

        AddressBookService service = new AddressBookService();

        Contact c = new Contact("Vaidik","Choudhary","","Bhopal","MP","","","");
        service.addContact("personal", c);

        assertEquals(1, service.searchByCity("bhopal").size());
    }

    @Test
    public void givenMultipleContactsSameCity_whenSearch_shouldReturnAll() {

        AddressBookService service = new AddressBookService();

        service.addContact("personal",
                new Contact("Vaidik","Choudhary","","Bhopal","MP","","",""));

        service.addContact("office",
                new Contact("Rahul","Verma","","Bhopal","DL","","",""));

        assertEquals(2, service.searchByCity("Bhopal").size());
    }
}