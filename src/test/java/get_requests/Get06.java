package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {

    // Given
    // https://restful-booker.herokuapp.com/booking/22
    // When
    // User send a GET request to the URL
    // Then
    // HTTP Status Code should be 200
    // And
    // Response content type is “application/json”
    // And
    // Response body should be like;
    // {
    //     "firstname": "John",
    //         "lastname": "Smith",
    //         "totalprice": 111,
    //         "depositpaid": true,
    //         "bookingdates": {
    //     "checkin": "2018-01-01",
    //             "checkout": "2019-01-01"
    // },
    //     "additionalneeds": "Breakfast"
    // }


    @Test
    public void Get() {

        //set base usrl
        spec.pathParams("first", "booking",
                "second", 22);

        //request
        Response response = given().
                spec(spec).
                when().
                get("{first}/{second}");
        response.prettyPrint();


        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast")
                );


        // second way

        System.out.println("****************************************************");

        JsonPath json = response.jsonPath();
        System.out.println("first name : " + json.getString("firstname"));
        System.out.println("total price : " + json.getInt("totalprice"));

        //response data icerisindeki degerlere ulasmak icin jsonpath kullanilir

        System.out.println("****************************************************");

        assertEquals("John", json.getString("firstname"));
        assertEquals("Smith", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertEquals("2019-01-01", json.getString("bookingdates.checkout"));
        assertEquals("Breakfast", json.getString("additionalneeds"));

    }
}
