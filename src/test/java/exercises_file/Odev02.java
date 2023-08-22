package exercises_file;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresBaseUrl;

import static io.restassured.RestAssured.given;

public class Odev02 extends RegresBaseUrl {

    /*
  Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    @Test
    public void test02(){
        //Set the url
        spec.pathParams("first", "users", "second", 23);

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
    }
}
