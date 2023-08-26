package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuAppBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking
    When
    User send a request to the URL
            Then
    Status code is 200
    And
    Among the data there should be someone whose firstname is "John" and last name is "Smith"
     */

    @Test
    public void get() {
        //1. Set Base Url
        spec.pathParam("first","booking").
                queryParams("firstname","John","lastname","Smith"); //path parametresini olusturduk
        //Set Expected data
        //3. Request
        Response response=given(spec).
                when().
                get("{first}");
        response.prettyPrint();
        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(Matchers.containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));

        assertTrue(response.asString().contains("bookingid"));
    }
}
    //setAccept() methodu get isleminde yapilan sorguda hangi data tipinde islem yapacaksak onu belirtmek icin kullanilir