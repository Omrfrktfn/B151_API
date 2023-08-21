package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13ObjectMapperPojo extends JsonPlaceHolderBaseUrl {

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
/*
        String body = "{\n" +
                "  \"userId\": 10,\n" +
                "  \"id\": 198,\n" +
                "   \"title\": \"quis eius est sint explicabo\",\n" +
                "   \"completed\": true\n" +
                "        }";


 */

      String body=  JsonPlaceHolderTestData.convertJsonToStringFormat(10,
                "quis eius est sint explicabo",
                true);

        JsonPlaceHolderPojo expectedDataPojo = ObjectMapperUtils.convertJsonToJava(body, JsonPlaceHolderPojo.class);
        System.out.println("expectedDataPojo = " + expectedDataPojo);

        System.out.println("**********************************");

        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10, "quis eius est sint explicabo", true);
        System.out.println("expectedData = " + expectedData);

        System.out.println("**********************************");

        Response response = given(spec).
                when().
                get("{first}/{second}");
        response.prettyPrint();

        System.out.println("**********************************");

        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataPojo.getUserId(), actualData.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualData.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualData.getCompleted());


    }
}
