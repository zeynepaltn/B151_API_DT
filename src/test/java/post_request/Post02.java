package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuAppBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */
    @Test
    public void post() {
        //Set Url
        spec.pathParam("first","booking");
        //Set Expected Data
        Map<String,String> bookingMap=new HerOkuAppTestData().bookingDateMapper("2021-09-09","2021-09-21");
        Map<String, Object> expectedDataMap=new HerOkuAppTestData().expecttedDataMapper("John","Doe",11111,true, bookingMap,null);
        System.out.println("Expected Data Map= "+expectedDataMap);
        //Send request get Response
        Response response=given(spec).
                body(expectedDataMap).//post request oldugu icin body olmasi lazim
                when().
                post("{first}");
        response.prettyPrint(); //TestData icerisinde setContenType(ContentType.Json) eklemezsek (internal server error) hata aliriz

        //Do Assertion
        Map<String, Object> actualDataMap=response.as(HashMap.class);
        JsonPath json=response.jsonPath(); //2. yol assertion icin gerekli


        assertEquals(200,response.statusCode());
        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(bookingMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        //2. yol assertion
        assertEquals(bookingMap.get("checkout"),json.getString("booking.bookingdates.checkout"));
    }
}
