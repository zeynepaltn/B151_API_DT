package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

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
        if(firstname!=null) {
            expecttedDataMap.put("firstname", firstname);
        }
        if(lastname!=null) {
            expecttedDataMap.put("lastname", lastname);
        }
        if(totalprice!=null) {
            expecttedDataMap.put("totalprice", totalprice);
        }
        if(depositpaid!=null) {
            expecttedDataMap.put("depositpaid", depositpaid);
        }
        if(bookingDates!=null) {
            expecttedDataMap.put("bookingdates", bookingDates);
        }
        if (additionalneeds != null) {
            expecttedDataMap.put("additionalneeds", additionalneeds);
        }
            return expecttedDataMap;

    }


}