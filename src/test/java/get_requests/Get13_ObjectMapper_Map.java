package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13_ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send GET Request to the URL
        Then
            Status code is 200
        And response body is like
            {
                "userId": 10,
                "id": 198,
                "title": "quis eius est sint explicabo",
                "completed": true
            }
    */

    @Test
    public void get13() {
        //Set the URL
        spec.pathParams("first","todos","second",198);
        //Set the expected Data
        //read value methodu istedigmiz string istedigmiz class'a donusturur
        String body="{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
        Map<String, Object> expectedData=ObjectMapperUtils.convertJsonToJava(body, HashMap.class);//static method oldugu icin class isimi ile ulasabiliyoruz
                                                    //Bu method ile body deki veriler key\value seklinde dizilecek bu yuzden map konteynirina koyuyoruz
        System.out.println("expected Data = " + expectedData);
        //Send the request get the response
        Response response =given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertions
        Map<String,Object> actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actual Data = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));//expected datam bir map konteynirinda o yuzden get ile ulasiyorum
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
