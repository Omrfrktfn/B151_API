package exercises_file;

import exercises_urls_pojo.BaseUrlTeam;
import exercises_urls_pojo.DisDataPojo;
import exercises_urls_pojo.IcDataPojo;
import io.restassured.response.Response;
import org.junit.Test;

import javax.xml.parsers.SAXParser;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TeamDeneme extends BaseUrlTeam {

    /*
    Given
         https://restful-booker.herokuapp.com/booking/535
     When
         I send GET Request to the URL
     Then
         Status code is 200
     And
         Response body is like:
              {
                     "firstname": "John",
                     "lastname": "Smith",
                     "totalprice": 111,
                     "depositpaid": true,
                     "bookingdates": {
                         "checkin": "2018-01-01",
                         "checkout": "2019-01-01"
                     },
                     "additionalneeds": "Breakfast"
                 }
     */

    @Test
    public void test01() {

        spec.pathParams("first", "booking",
                "second", 535);

        IcDataPojo icDatePojo = new IcDataPojo("2018-01-01", "2019-01-01");

        DisDataPojo expectedData = new DisDataPojo("Bob", "Smith", 111, true, icDatePojo, "Breakfast");

        System.out.println(expectedData);

        System.out.println("*********************************");

        Response response = given(spec).
                when().
                get("{first}/{second}");
        response.prettyPrint();

        System.out.println("*********************************");

        DisDataPojo actualData = response.as(DisDataPojo.class);
        System.out.println(actualData);

        System.out.println("*********************************");

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());


      //  assertEquals(icDatePojo.getCheckin(), actualData.getBookingdates().getCheckin());

        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());

        assertEquals(icDatePojo.getCheckout(), actualData.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }

}
