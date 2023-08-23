package SmokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuTestData;

import static SmokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_DeleteBooking extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/1

    When
        sent delete request
    Then
        StatusCode 201
    And
        body : Created

     */

    @Test
    public void delete() {

        spec.pathParams("first", "booking",
                "second", bookingId);

        String expectedData = "Created";

        Response response = given(spec).
                when().
                delete("{first}/{second}");
        response.prettyPrint();

        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());

        System.out.println("**********************************************************************");

    }

}
