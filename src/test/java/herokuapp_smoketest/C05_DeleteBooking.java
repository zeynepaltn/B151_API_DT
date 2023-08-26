package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_DeleteBooking extends HerokuAppBaseUrl {
/*
Given
    https://restful-booker.herokuapp.com/booking/1

When
    sent delete request
Then
    StatusCode 201
And
    body : Created

 */

    @Test
    public void delete() {
        //Set Url
        spec.pathParams("first", "booking", "second", bookingId);
        //Set expected Data
        String expectedData="Created";
        //SEnd request and get response
        Response response=given(spec).when().delete("{first}/{second}");
        response.prettyPrint();
        //Do assertions
        assertEquals(201,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
