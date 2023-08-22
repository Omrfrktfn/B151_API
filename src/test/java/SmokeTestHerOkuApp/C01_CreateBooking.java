package SmokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookinIdPojo;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreateBooking extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
                {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Sent post request
    Then
        Statuscode 200
    And
        Body:
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
     */

    public static Integer bookingId;

    @Test
    public void post() {

        spec.pathParam("first", "booking");

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2018-01-01");

        BookingPojo expectedData = new BookingPojo("Jim",
                "Brown", 111, true, bookingDatesPojo, "Breakfast");

        Response response = given(spec).body(expectedData).
                when().
                post("{first}");
        response.prettyPrint();

        BookinIdPojo actualData = convertJsonToJava(response.asString(), BookinIdPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingId = response.jsonPath().getInt("bookingid");


    }

}
