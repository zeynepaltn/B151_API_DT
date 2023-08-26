package put_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos/198
      2) {
          "userId": 21,
          "title": "Wash the dishes",
          "completed": false
         }
     When
     I send PUT Request to the Url
    Then
      Status code is 200
      And response body is like   {
            "userId": 21,
            "title": "Wash the dishes",
            "completed": false
           }
     */

    @Test
    public void put01() {
        //Set base Url
        spec.pathParams("first","todos","second",198);
        //Set expected Data
        Map<String, Object> expectedData=new HashMap<>();
        expectedData.put("userId",21);
        expectedData.put("title","Wash the dishes");
        expectedData.put("completed",false);

        //Send request get response
        Response response =given(spec).body(expectedData).when().put("{first}/{second}");
        //response.prettyPrint(); //body'i simdilik kalabalik olmasin diye yazdirmadim

        //Do Assertions
        Map<String,Object> actualData=response.as(HashMap.class);//serialization yaptik
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }



    @Test
    public void put02() {
        //Set base Url
        spec.pathParams("first","todos","second",198);
        //JsonPlaceHolderTestData isimli bi class olusturup icerisinde map ile body'i iceren bir method olusturduk
        // obje olusturup bu methodu cagirdik
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String, Object> expectedData=obj.expectedDataMapper(21,"Wash the dishes",false);
        System.out.println("expected Data = " + expectedData);

        //3. Send the request and get the response
        Response response = given(spec).body(expectedData).when().put("{first}/{second}"); // Serialization = Java Map datasının Json datasına çevrilmesidir
        response.prettyPrint();

        //4. Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        // Status code doğrulaması
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
