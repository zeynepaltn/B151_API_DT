package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {
     /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */


    @Test
    public void post() {
        //1. Set Url
        spec.pathParam("first","todos");

        //2.  Set Expected Data
        String payload="{\n" +
                "\"userId\": 55,\n" +
                "\"title\": \"Tidy your room\",\n" +
                "\"completed\": false\n" +
                "}";

        //3. Sent request get respond
        Response response=given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        assertEquals(201,response.statusCode());

        //Do assertion
        JsonPath json =response.jsonPath();
        assertEquals(55,json.getInt("userId"));
        assertEquals("Tidy your room",json.getString("title"));
        assertFalse(json.getBoolean("completed"));
        assertEquals(201,json.getInt("id"));

    }


    @Test
    public void post01Map() {
        //1. Set Url
        spec.pathParam("first","todos");
        //2.  Set Expected Data
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        //3. sent request and get response
        Response response=given(spec).body(expectedData).when().post("{first}");
        //Biz map gonderiyoruz karsi tarafa ama bizden json bekleniyor, bu java objesini jason objesine donusturmeliyiz
        //Java object'in Json object'e cevrilmesine serialization deniyor
        //Bunu yapabilmek icin de POM.xml'e Jackson repository'sini ekleyecegiz
        //Deserialization ---> Json Objesinin Java Objesine cevrilmesidir.
        response.prettyPrint();


        //Do assertions
        //once json objesini java objesine ceviecegiz
        Map<String,Object> actualdata=response.as(HashMap.class);//Deserialization
        assertEquals(expectedData.get("userId"),actualdata.get("userId"));
        assertEquals(expectedData.get("title"),actualdata.get("title"));
        assertEquals(expectedData.get("completed"),actualdata.get("completed"));
        assertEquals(201,actualdata.get("id"));



    }
}
