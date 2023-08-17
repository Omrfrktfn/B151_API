package get_requests;

import base_urls.HerOkuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuTestData;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    //  Given
    //  https://restful-booker.herokuapp.com/booking/91
    //  When
    //  I send GET Request to the url
    //          Then
    //  Response body should be like that;
    // {
    //    "firstname": "Jane",
    //    "lastname": "Doe",
    //    "totalprice": 111,
    //    "depositpaid": true,
    //    "bookingdates": {
    //        "checkin": "2018-01-01",
    //        "checkout": "2019-01-01"
    //    },
    //    "additionalneeds": "Extra pillows please"
    //}


    @Test
    public void get09() {
        //set base usrl
        spec.pathParams("first", "booking",
                "second", 92);

        Map<String, String> bookingDatesMap = new HashMap<>(); // ilk önce iç map oluşturulur
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");
        System.out.println("bookingDatesMap = " + bookingDatesMap);

        System.out.println("***************************************************");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Jane");
        expectedDataMap.put("lastname", "Doe");
        expectedDataMap.put("totalprice", 111);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingDatesMap);
        expectedDataMap.put("additionalneeds", "Extra pillows please");
        System.out.println("expectedDataMap = " + expectedDataMap);

        System.out.println("***************************************************");
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        System.out.println("***************************************************");
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        System.out.println("***************************************************");

        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"), ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        //   assertEquals((bookingDatesMap.get("checkin")), ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"), ((Map) actualDataMap.get("bookingdates")).get("checkout"));
        //     assertEquals((bookingDatesMap.get("checkout")), ((Map) actualDataMap.get("bookingdates")).get("checkout"));

        assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));


        //Object map = new HashMap<>();
        //((Map) map).get("");

    }


    @Test
    public void test() {
        // Set Url
        spec.pathParams("first", "booking"
                , "second", 4557);


        // Set expected data

        Map<String, String> bookingMap = new HerOkuTestData().
                bookingDateMapper("2018-01-01", "2019-01-01");

        Map<String, Object> expectedDataMap = new HerOkuTestData().
                expecttedDataMapper("Jane", "Doe"
                        , 111, true, bookingMap, "Extra pillows please");

        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // System.out.println("***************************************************");
        // Map<String, Object> actualDataMap = response.as(HashMap.class);
        // System.out.println("actualDataMap = " + actualDataMap);

        // assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        // assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        // assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        // assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

        // assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkin"), ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        // //   assertEquals((bookingDatesMap.get("checkin")), ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        // assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"), ((Map) actualDataMap.get("bookingdates")).get("checkout"));
        // //     assertEquals((bookingDatesMap.get("checkout")), ((Map) actualDataMap.get("bookingdates")).get("checkout"));

        // assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));


        JsonPath json = response.jsonPath();
        assertEquals(200, response.statusCode());
        assertEquals(expectedDataMap.get("firstname"), json.getString("firstname"));
        assertEquals(expectedDataMap.get("lastname"), json.getString("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), json.getInt("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), json.getBoolean("depositpaid"));
        assertEquals(bookingMap.get("checkin"), json.getString("bookingdates.checkin"));
        assertEquals(bookingMap.get("checkout"), json.getString("bookingdates.checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"), json.getString("additionalneeds"));

    }


}
