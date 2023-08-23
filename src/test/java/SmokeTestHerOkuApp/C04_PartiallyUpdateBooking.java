package SmokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerOkuTestData;

import java.util.HashMap;
import java.util.Map;

import static SmokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PartiallyUpdateBooking extends HerOkuAppBaseUrl {
    /*
        Given
          https://restful-booker.herokuapp.com/booking/:id
        And

            {
            "firstname" : "Sakin",
            "lastname" : "Brown"
            }

            When
                sent patch request
            Then
                statuscode 200
            And
                            {
                    "firstname" : "James",
                    "lastname" : "Browni",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }

     */
    @Test
    public void patch() {

        spec.pathParams("first", "booking",
                "second", bookingId);


        Map<String, Object> payLoad = new HerOkuTestData().expecttedDataMapper("Sakin",
                "Browny", null, null, null, null);
        System.out.println("payLoad = " + payLoad);

        System.out.println("**********************************************************************");

        Response response = given(spec).
                body(payLoad).
                when().
                patch("{first}/{second}");
        response.prettyPrint();


        Map<String, Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.get("firstname"), actualData.get("firstname"));
        assertEquals(payLoad.get("lastname"), actualData.get("lastname"));

        System.out.println("**********************************************************************");


    }
}
