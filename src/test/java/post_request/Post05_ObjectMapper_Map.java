package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;
import static org.junit.Assert.assertEquals;

public class Post05_ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
    /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
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


    //response as methodu sadece json ve java datalari arasinda donusum yapmaz.
    //jackson-databind ve jackson.datatype dependency leri ile birlikte response as() methodu
    // Integer variable i String'e, String'i Local Date'e gibi farkli veri tiplerini birbirine cevirebilmemize
    // imkan tanir

    @Test
    public void test05() throws JsonProcessingException {
        //Set the Url
        spec.pathParam("first","todos");
        //Set the expected Data
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMapper=obj.expectedDataMapper(55,"Tidy your room", false);
        System.out.println("expected Data = " + expectedDataMapper);
        //Send the request and get the response
        Response response=given(spec).body(expectedDataMapper).when().post("{first}");
        response.prettyPrint();
        //Do assertions
        ObjectMapper objectMapper=new ObjectMapper();
        //Map<String, Object> actualData1=response.as(HashMap.class); Asagida yapacahimiz actualData piyasada daha cok kullaniliyor
        Map<String, Object> actualData=objectMapper.readValue(response.asString(), HashMap.class);
        //Normalde esitligin saginda response.as(HashMap.class); yazip onu bir Map icine koyuyorduk.
        // Ancak burada gelen datayi once bir String'e ceviriyor. response bize gelen json ve biz json'i
        // once string'e ceviriyoruz. Sonra da onu HashMap class'ina koyuyoruz
        System.out.println("actual Data = " + actualData);
        assertEquals(201,response.statusCode());
        assertEquals(expectedDataMapper.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMapper.get("title"),actualData.get("title"));
        assertEquals(expectedDataMapper.get("completed"),actualData.get("completed"));

    }
}
