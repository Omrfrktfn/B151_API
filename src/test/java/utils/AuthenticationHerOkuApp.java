package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static String getToken() {

        String body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response = given().
                body(body).
                when().
                post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();

        return "";
    }



}
