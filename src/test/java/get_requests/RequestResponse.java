package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//Selenium'u, Web tabanlı uygulamaları test etmek için kulaniriz
//Rest-assured kutuphanesini API testleri icin kullaniriz

public class RequestResponse {
    /*
    1- Manuel API testi icin Postman kullniyoruz
    2- API otomasyon  testi icin REST Assured kutuphanesini kullaniyoruz
    3- Otomasyon kodlarinin yazimi icin su adimlari izleriz:
            a-Gereksinimleri anlama
            b-Test senaryosu yazma
                --Test senaryosu yazmak icin Gherkin dilini kullaniyoruz
                    -Given: Endpoint, body...
                    -When: get, put, post gibi islemler
                    -Then: Assertions, Close...
                    -And: Coklu islemlerin ard arda yapildigi zaman kullanilir
            c- Otomasyon kodlarini yazarken su adimlari izleriz
                1- Set the URL
                2- Set the expected data
                3- Send the request and get the reponse
                4- Do assertion
     */

    public static void main(String[] args) {
        //Get testi nasil yapilir?
        String url="https://petstore.swagger.io/v2/pet/752";
        Response response=given().when().get(url);
        response.prettyPrint();

        //Status code nasil yazdirilir?
        System.out.println("status code " +response.statusCode());

        //Content Type nasil yazdirilir?
        System.out.println("Content Type "+response.contentType());

        //Status Line nasil yazdirilir?
        System.out.println("status Line= " + response.statusLine());

        //Header'daki veriler nasil yazdirilir?
        System.out.println("Header Server ="+response.header("Server"));

        //Connection nasil yazdiririz?
        System.out.println("Header Connection ="+response.header("Connection"));

        //Headers nasil yazdirilir?
        System.out.println("Headers "+response.headers());

        //Time nasil yazdirilir?
        System.out.println("Time in milliseconds: "+response.time());


    }

}
