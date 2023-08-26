package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06_GetDeletedBooking extends HerokuAppBaseUrl {
/*
Given
        https://restful-booker.herokuapp.com/booking/:id
When
        Send Get request
Then
        Statuscode 200
And
        Body: Not Found

 */

    @Test
    public void getDeleted() {
        spec.pathParams("first", "booking", "second", bookingId);

        String expectedData="Not Found";

        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}
