package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookinIdPojo;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.HerOkuTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends HerOkuAppBaseUrl {
    /*
            Given
              1)  https://restful-booker.herokuapp.com/booking
              2) {
                    "firstname": "Ali",
                    "lastname": "Can",
                    "totalprice": 999,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-09-21",
                        "checkout": "2021-12-21"
                     },
                     "additionalneeds": "Breakfast"
                  }
            When
                I send POST Request to the URL
            Then
                Status code is 200
            And
                Response body is like
                     {
                        "bookingid": 16,
                        "booking" :{
                            "firstname": "Ali",
                            "lastname": "Can",
                            "totalprice": 999,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2021-09-21",
                                "checkout": "2021-12-21"
                            },
                            "additionalneeds": "Breakfast"
                         }
                      }
         */
    @Test
    public void post04() {

        spec.pathParam("first", "booking");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");

        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true,
                bookingDates, "Breakfast");

        System.out.println(expectedData);
        System.out.println("***************************");

        BookinIdPojo data1 = new BookinIdPojo(16, expectedData);
        System.out.println(data1);

        Response response = given(spec).when().body(expectedData).post("{first}");
        response.prettyPrint();

        BookinIdPojo actualData = response.as(BookinIdPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());

        assertEquals(data1.getBooking().getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(data1.getBooking().getLastname(), actualData.getBooking().getLastname());
        assertEquals(data1.getBooking().getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(data1.getBooking().getDepositpaid(), actualData.getBooking().getDepositpaid());

        assertEquals(data1.getBooking().getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(data1.getBooking().getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

        assertEquals(data1.getBooking().getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());


    }


}
