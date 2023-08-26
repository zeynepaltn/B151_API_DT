package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PartiallyUpdateBooking extends HerokuAppBaseUrl {
    /*
    Given
      https://restful-booker.herokuapp.com/booking/:id
    And

        {
        "firstname" : "Sakin",
        "lastname" : "Brown"
        }

        When
            sent patch request
        Then
            statuscode 200
        And
                        {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }

 */

    @Test
    public void patch() {
        spec.pathParams("first", "booking", "second", bookingId);
        //Set expected Data
        Map<String,Object> payload=new HerOkuAppTestData().expecttedDataMapper("Sakin","Browny",null,null,null,null);
        //Yukarida patch islemi ile sadece bir bolumu degistirdigimiz icin diger bolumleri null olarak yazdik.
        System.out.println("payload = " + payload);
        //Send request and get response
        Response response=given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();
        //Do assertions
        Map<String, Object> actualdata=convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(payload.get("firstname"),actualdata.get("firstname"));
        assertEquals(payload.get("lastname"),actualdata.get("lastname"));



    }
}
