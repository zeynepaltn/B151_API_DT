package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
    1) https://jsonplaceholder.typicode.com/todos/198
    2) {
          "title": "Wash the dishes"
        }
When
  I send PATCH Request to the Url
Then
      Status code is 200
      And response body is like   {
                        "userId": 10,
                        "title": "Wash the dishes",
                        "completed": true,
                        "id": 198
                       }
     */

    @Test
    public void patch() {
        //Set Url
        spec.pathParams("first","todos","second","198");
        //Set expected Data:
        Map<String, Object> payLoad=new JsonPlaceHolderTestData().
                expectedDataMapper(null,"Wash the dishes",null);
        Map<String, Object> expectedDataMap=new JsonPlaceHolderTestData().
                expectedDataMapper(10,"Wash the dishes",true);
        expectedDataMap.put("id",198);
        System.out.println("payLoad = " + payLoad);
        System.out.println("expectedDataMap = " + expectedDataMap);

        //Send request get Response
        Response response=given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion sadece patch yaptigimiz alanda
        Map<String, Object> actualDataMap=response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("title"),actualDataMap.get("title"));
        //Body'nin tamami assert edilecekse 1. expectedDataMap isminde yeni bir map olustururuz veya
        //2. payload map'ine response aldiktan sonra guncelleme yapar ve bekledigimiz verileri gireriz

        //tum alanlarda assertion su sekilde yapacagiz
        assertEquals(200,response.statusCode());
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));


    }
//Gurkay Hoca aksam dersi ayni soru cozumu
    @Test
    public void patch01() {
        // Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMapper(null, "Wash the dishes", null);

        // Send the request and get the response
        Response response = given(spec).body(expectedData).when().patch("{first}/{second}");
        response.prettyPrint();

    }









}
