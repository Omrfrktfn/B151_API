package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuTestData {

    public Map<String, String> bookingDateMapper(String checkin, String checkout) {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout", checkout);
        return bookingDatesMap;
    }

    public Map<String, Object> expecttedDataMapper(String firstname, String lastname, Integer totalprice,
                                                   Boolean depositpaid, Map<String, String> bookingDates,
                                                   String additionalneeds) {

        Map<String, Object> expecttedDataMap = new HashMap<>();

        expecttedDataMap.put("firstname", firstname);
        expecttedDataMap.put("lastname", lastname);
        expecttedDataMap.put("totalprice", totalprice);
        expecttedDataMap.put("depositpaid", depositpaid);
        expecttedDataMap.put("bookingdates", bookingDates);
        expecttedDataMap.put("additionalneeds", additionalneeds);
        return expecttedDataMap;

    }


}
