package get_requests;


//Given  https://jsonplaceholder.typicode.com/todos
//When   I send a GET request to the Url
//And    Accept type is "application/json"
//Then   HTTP Status Code should be 200
//And    Response format should be "application/json"
//And    200 adet todos olmalı
//And    başlıklarından birisi  "quis eius est sint explicabo" olmalı
//And    userIds ler arasında 2, 7, and 9 bulunmalı

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {
    @Test
    public void Get() {
        //1. Set Url
        //String url="https://jsonplaceholder.typicode.com/todos";//Bu tercih edilmez
        spec.pathParam("first","todos"); //first=ilk parametreyi kast ediyoruz, istedigimiz ismi kullanabiliriz fakat en cok bu kullaniliyor

        //2. set expected data olmayacak oy uzden send request ile devam ederiz
        //3. Send request
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                //body("[0].title", equalTo("delectus aut autem"));
                body("id",hasSize(200),"title",
                        hasItem("quis eius est sint explicabo"),
                        "id",hasItems(2,7,9));

    }
    //donen datada bize liste gelmisse ozel bir bilgiye ulasmak icin index kullanilir
}
