package get_requests;
import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class Get12 extends GoRestBaseUrl {
    /*
          Given
              https://gorest.co.in/public/v1/users/4496516
          When
              User send GET Request to the URL
          Then
              Status Code should be 200
          And
              Response body should be like
            {
              "meta": null,
              "data": {
                    "id": 4495606,
                    "name": "Chitrali Sinha",
                    "email": "sinha_chitrali@bergstrom.test",
                    "gender": "male",
                    "status": "inactive"
                }
             }
      */
    @Test
    public void get12() {
        // Set the URL
        spec.pathParams("first", "users", "second", 4496516);
        // Set the expected Data
        GoRestDataPojo goRestData = new GoRestDataPojo("Narinder Pandey", "narinder_pandey@franecki-mcglynn.example", "male", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestData);
        System.out.println(expectedData);
        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        // Do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMeta(), actualData.getMeta());
        assertEquals(goRestData.getName(), actualData.getData().getName());
        assertEquals(goRestData.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestData.getGender(), actualData.getData().getGender());
        assertEquals(goRestData.getStatus(), actualData.getData().getStatus());

    }
}
