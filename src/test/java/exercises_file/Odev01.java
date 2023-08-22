package exercises_file;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresBaseUrl;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev01 extends RegresBaseUrl {
    @Test
    public void odev() {

        /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

        //Set the url
        spec.pathParams("first", "users", "second", 3);

        //Set the expected data
        //Send the request get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        //Do assertion
        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

        //bu kisim alternatif, aslinda yapmamiza gerek yok, cünkü then zaten sorgu yapiyor
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());

        //asString();-->response un icindeki degeri stringe ceviriyor, int rakamlari da string olarak elde ederiz

    }
}
