package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13ObjectMapperMap extends JsonPlaceHolderBaseUrl {

/*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send GET Request to the URL
    Then
        Status code is 200
    And response body is like
        {
            "userId": 10,
            "id": 198,
            "title": "quis eius est sint explicabo",
            "completed": true
        }
*/

    @Test
    public void Get13() {
        spec.pathParams("first", "todos",
                "second", 198);

        String body = "{\n" +
                "  \"userId\": 10,\n" +
                "  \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "        }";
        //butun body string'e cevrilmis oldu.

        Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(body, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given(spec).
                when().
                get("{first}/{second}");
        response.prettyPrint();

        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

       // Map<String, String> actualData = response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
