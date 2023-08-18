package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.HerOkuTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

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




    }


}
