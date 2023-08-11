package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class Get02Deneme {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status code 404 olmalı
        And
            Status Line "HTTP/1.1 404 Not Found" olmalı
        And
            Response body "Not Found" içermeli
        And
            Response body "TechProEd" içermemeli
        And
            Server değeri "Cowboy" olmalı
    */

    @Test
    public void get02() {


        String url = "https://restful-booker.herokuapp.com/booking/0";

        Response response = given().when().get(url);
        response.prettyPrint();

        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .and().assertThat();

        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("TechproEd"));
        assertEquals(response.asString(),"Not Found");
        assertEquals(response.header("Server"), "Cowboy");


    }
}
