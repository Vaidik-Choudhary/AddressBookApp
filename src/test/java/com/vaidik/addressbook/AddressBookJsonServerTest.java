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
    
    @Test
    public void givenNewContact_whenAddedToJsonServer_shouldReturnCreated() {

        String newContact = """
            {
              "firstName": "Amit",
              "lastName": "Sharma",
              "address": "",
              "city": "Mumbai",
              "state": "MH",
              "zip": "400001",
              "phoneNumber": "7777777777",
              "email": "amit@gmail.com"
            }
            """;

        Response response =
                RestAssured
                        .given()
                        .contentType("application/json")
                        .body(newContact)
                        .when()
                        .post("http://localhost:3000/contacts");

        assertEquals(201, response.getStatusCode());

    }
    
    @Test
    public void givenExistingContact_whenUpdated_shouldReturnSuccess() {

        String updatedContact = """
            {
              "firstName": "Tarus",
              "lastName": "Prabhat",
              "address": "",
              "city": "Mumbai",
              "state": "MH",
              "zip": "411001",
              "phoneNumber": "9999999999",
              "email": "tp@gmail.com"
            }
            """;

        Response response =
                RestAssured
                        .given()
                        .contentType("application/json")
                        .body(updatedContact)
                        .when()
                        .put("http://localhost:3000/contacts/1");

        assertEquals(200, response.getStatusCode());

    }
}
