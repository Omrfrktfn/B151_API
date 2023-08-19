package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {


 /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */

    @Test
    public void delete01() {

        spec.pathParams("first", "todos",
                "second", 198);

        Map<String, String> expectedData = new HashMap<>();
        Response response = RestAssured.given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());
        //1
        assertEquals(expectedData, actualData);
        //2
        assertEquals(0, actualData.size());
        //3
        assertTrue(actualData.isEmpty());
    }

}
