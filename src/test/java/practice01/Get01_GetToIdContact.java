package practice01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Get01_GetToIdContact {

    @Test
    public void get01() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        //    RestAssured.basePath = "/contactList/64d7e14436c2810013fe7d76";
        RestAssured.basePath = "/contacts/64d7e14436c2810013fe7d76";

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGQ3YzRhMTlhNjk0ZTAwMTMwZjFmZmMiLCJpYXQiOjE2OTE4NjM2MjJ9.7HfrBB2E1WK5hrMWQMV6BA6zBisfhpKDBSWnB4QILtc";


        Response reponse =
                given()
                        .auth()
                        .oauth2(token)
                        .when()
                        .get();
        reponse.prettyPrint();

        reponse
                .then()
                .body("firstName",equalTo("omer"))
                .body("lastName",equalToIgnoringCase("tufan"))
                .body("email",not(equalTo("fsfaf@fake.com")))
                .body("street1",containsString("1 Main "))
                .body("city",startsWith("Any"))
                .body("city",endsWith("town"))
                .body("stateProvince",anyOf(equalTo("KS"), equalTo("CA")))
                .body("country", allOf(equalTo("USA"),equalToIgnoringCase("usa")))
                .body("__v", greaterThan(-1));


    }


}
