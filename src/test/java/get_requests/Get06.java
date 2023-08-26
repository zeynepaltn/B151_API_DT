package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/22
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
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
    public void get() {
        spec.pathParams("first","booking","second",22);
        Response response=given().spec(spec).get("{first}/{second}");
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast")
                );

        //2. Yol: Response data icerisindeki degerlere ulasmak icin Jsonpath kullanilir
        JsonPath json=response.jsonPath();
        System.out.println(json.getString("firstname"));
        System.out.println(json.getInt("totalprice"));
        System.out.println(json.getBoolean("depositpaid"));
        assertEquals("John",json.getString("firstname"));
        assertEquals("Smith",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertEquals(json.getBoolean("depositpaid"), true);
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        assertEquals("Breakfast",json.getString("additionalneeds"));

        //SoftAssertion ile yapalim
        //Soft asserts TestNg ile gelen bir ozellik oldugu icin TestNg pom'a yuklenmeli
        //1. Adim soft assertion objesi olusturuyoruz
        SoftAssert softAssert = new SoftAssert();

        //2. Adim assertion yapilacak
        softAssert.assertEquals(json.getString("firstname"),"John","first name uyusmadi");
        softAssert.assertEquals(json.getString("lastname"),"Smith","last name uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"),111,"total price uyusmadi");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"deposit odenmemis olabilir");
        softAssert.assertEquals(json.getString("bookingdates.checkin") , "2018-01-01","check in tarihi farkli");
        softAssert.assertEquals(json.getString("bookingdates.checkout") , "2019-01-01","check out tarihi farkli");
        softAssert.assertEquals(json.getString("additionalneeds") , "Breakfast","additional needs farkli");
        //3. Adimda assertall yapacagiz
        softAssert.assertAll();

    }//test
}//class
