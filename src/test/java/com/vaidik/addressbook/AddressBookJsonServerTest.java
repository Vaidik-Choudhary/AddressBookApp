package com.vaidik.addressbook;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookJsonServerTest {


	
    @Test
    public void givenJSONServer_whenContactsFetched_shouldReturnRecords() {

        Response response = RestAssured.get("http://localhost:3000/contacts");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode());

    }
}
