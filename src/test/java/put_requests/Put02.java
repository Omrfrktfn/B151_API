package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyResponsePojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Put02 extends DummyRestApiBaseUrl {
    /*
    Given
    https://dummy.restapiexample.com/api/v1/update/21
    And
    Request body: {
        "employee_name": "Ali Can",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image"
    }
    When
    User sends PUT request
    Then
    Status code should be 200
    And
    Response body should be like the following:
    {
        "status": "success"
        "data": {
        "employee_name": "Ali Can"
        "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image"
    },
        "message": "Successfully! Record has been updated."
    }

     */

    @Test
    public void put01() {
        spec.pathParams("first", "update",
                "second", 21);

      // String str = "{\n" +
      //         "        \"employee_name\": \"Ali Can\",\n" +
      //         "                \"employee_salary\": 111111,\n" +
      //         "                \"employee_age\": 23,\n" +
      //         "                \"profile_image\": \"Perfect image\"\n" +
      //         "    }";

      // Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(str, HashMap.class);

        DummyDataPojo payload = new DummyDataPojo("Ali Can",
                111111,
                23,
                "Perfect image");
        System.out.println("payload = " + payload);

        System.out.println("************************************");

        Response response = given(spec).
                body(payload).
                when().
                put("{first}/{second}");
        response.prettyPrint();

     //  expectedData.put("status", "success");
     //  expectedData.put("message", "Successfully! Record has been updated.");

        System.out.println("************************************");

        //DummyDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyDataPojo.class);

        DummyResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyResponsePojo.class);
     //   Map<String , Object> actualData2 = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(200, response.statusCode());
        assertEquals(payload.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(payload.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(payload.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(payload.getProfile_image(), actualData.getData().getProfile_image());

        assertEquals("success", actualData.getStatus());
        assertEquals("Successfully! Record has been updated.", actualData.getMessage());

     //   assertEquals(expectedData.get("status"),actualData2.get("status"));
      //  assertEquals(expectedData.get("message"),actualData2.get("message"));

    }
}
