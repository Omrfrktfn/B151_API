package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
//import static jdk.internal.net.http.frame.Http2Frame.asString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    // Given
    // https://restful-booker.herokuapp.com/booking
    // When
    // User send a request to the URL
    //         Then
    // Status code is 200
    // And
    // Among the data there should be someone whose firstname is "Sally" and last name is "Brown"

    @Test
    public void Get() {

        //set base usrl
        spec.pathParam("first", "booking").
                queryParams("firstname", "John",
                        "lastname", "Smith");

        //request
        Response response = given(spec).
                when().
                get("{first}");
        response.prettyPrint();


        response
                .then()
                .statusCode(200)
                .body(Matchers.containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));

        assertTrue(response.asString().contains("bookingid"));
    }
}
