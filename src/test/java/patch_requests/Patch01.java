package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {


    /*
 Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
            }
     When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like   {
                            "userId": 10,
                            "title": "Wash the dishes",
                            "completed": true,
                            "id": 198
                           }
 */

    @Test
    public void patch() {
        // Set Url
        spec.pathParams("first", "todos"
                , "second", 198);

        // Expected data:
        Map<String, Object> payLoad = new JsonPlaceHolderTestData()
                .expectedDataMapper(null, "Wash the dishes", null);

        Map<String, Object> expectedDataMap = new JsonPlaceHolderTestData()
                .expectedDataMapper(10, "Wash the dishes", true);
        expectedDataMap.put("id", 198);

        System.out.println("payLoad = " + payLoad);
        System.out.println("expectedDataMap = " + expectedDataMap);

        // Sent req and get resp
        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        // Do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(payLoad.get("title"), actualDataMap.get("title"));
        //body nin tamamı assert edilecekse 1. expectedDataMap isminde yeni bir map oluşturup karşılaştırma yapılır
        //2.payload map ine respons aldıktansonra güncelleme yapar beklediğiniz verileri girersiniz

        // tüm alanların doğrulanması:

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));
    }

    @Test
    public void patch01() {
        spec.pathParams("first", "todos"
                , "second", 198);


        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        Map<String, Object> payload = obj.expectedDataMapper(null, "Wash the dishes", null);
        Map<String, Object> expectedDataMap = new JsonPlaceHolderTestData()
                .expectedDataMapper(10, "Wash the dishes", true);

        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());

        Map<String, Object> actualDataMap = response.as(HashMap.class);


        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));

    }


}
