package SmokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static SmokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C06_GetDeleteBooking extends HerOkuAppBaseUrl {
/*
Given
        https://restful-booker.herokuapp.com/booking/:id
When
        Send Get request
Then
        Statuscode 404
And
        Body: Not Found

 */

    @Test
    public void get() {
        spec.pathParams("first", "booking",
                "second", bookingId);

        String expectedData="Not Found";

        Response response = given(spec).
                when().
                get("{first}/{second}");
        response.prettyPrint();

        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());

    }

}
