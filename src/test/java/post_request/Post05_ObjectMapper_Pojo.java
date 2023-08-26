package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05_ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
     /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void post05Pojo() {
        //Set the Url
        spec.pathParam("first","todos");

        //Set the expected Data
        JsonPlaceHolderPojo expecteddata=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expected data = " + expecteddata);

        //Send the request and get the response
        Response response=given(spec).body(expecteddata).when().post("{first}");
        response.prettyPrint();

        //Do assertions
        JsonPlaceHolderPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        assertEquals(201,response.statusCode());
        assertEquals(expecteddata.getUserId(),actualData.getUserId());
        assertEquals(expecteddata.getTitle(),actualData.getTitle());
        assertEquals(expecteddata.getCompleted(),actualData.getCompleted());

    }
}

/*
    Suana kadar Api de 5 Assertion methodu gorduk
    Hamcrest Matcher
    Json Path - groovy language
    Response as -Map
    Response as -Pojo
    Object Mapper

     */
