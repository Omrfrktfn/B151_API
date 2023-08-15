package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {

    //Given
    //       1) https://jsonplaceholder.typicode.com/todos
    //        2)  {
    //    "userId": 55,
    //            "title": "Tidy your room",
    //            "completed": false
    //}
    //When
    //I send POST Request to the Url
    //        Then
    //Status code is 201
    //And
    //response body is like {
    //    "userId": 55,
    //            "title": "Tidy your room",
    //            "completed": false,
    //            "id": 201
    //  }


    @Test
    public void post() {

        spec.pathParam("first", "todos");

        String payLoad = "{\n" +
                "      \"userId\": 55,\n" +
                "      \"title\": \"Tidy your room\",\n" +
                "      \"completed\": false\n" +
                "   }";

        // spec.pathParam("first", "todos").
        //        body(payLoad);

        //sent request and response
        Response response = given(spec).
                body(payLoad).
                when().
                post("{first}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        assertEquals(55, json.getInt("userId"));
        assertEquals("Tidy your room", json.getString("title"));
        assertFalse(json.getBoolean("completed"));
        assertEquals(201, json.getInt("id"));

    }



    //Dinamik method
    @Test
    public void post01Map() {
        //set url
        spec.pathParam("first", "todos");

        //set expected data
        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        //  expectedData.put("id", "201"); // body'de id olmadigi icin girilmez

        //send request and response
        Response response = given(spec).
                body(expectedData).
                when().
                post("{first}");
        response.prettyPrint();
        //map json formatina cevrilmesi icin serialize yapilir
        //serialize java object'ini json object'ine cevirir

        //Serialization ---> Java Objesinin Json Objesine cevrilmesidir
        //Deserialization ---> Json Objesinin Java Objesine cevrilmesidir.

        //Deserialiazation =json objectsini java data objesine donustururdu.
        // response alip hasmap class'ina ait bir dataya cevirmis olduk
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(201, actualData.get("id"));

    }


}
