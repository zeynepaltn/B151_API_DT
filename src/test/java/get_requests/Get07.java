package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
    Given
           https://jsonplaceholder.typicode.com/todos
    When
       I send GET Request to the URL
    Then
       1)Status code is 200
       2)"Id"leri 190 dan büyük olanları konsola yazdırın
         "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
       3)"Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
       "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
       4)"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
         "delectus aut autem" başlığının id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test //test annotation'lari junit den al
    public void get() {
        //1. Set Url
        spec.pathParam("first", "todos");//url'den sonra gelen kismin path'ini veriyoruz
        //2. Set expected
        //3. Sent request and get respond
        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();//postman de manual check yapabiliriz o yuzden burda yazdirmaya gerek yok
        //Do Assertion
        //Status code is 200
        assertEquals(200, response.statusCode());

        //Groovy language list icindeki json'lari sorgulama yapip data geri cagirmamizi saglar
        JsonPath json = response.jsonPath();//response'daki veriyi jsonpath ile kullanilir hale getiriyoruz
        List<Object> list=json.getList("id");//butun id'leri siralar yazdirirsak
        List<Object> list1=json.getList("title");//butun title'leri siralar yazdirirsak

        //System.out.println(json.getList("title"));

        //id'si 190'dan buyuk olan tum itemlari yazdir
        List<Object> idList = json.getList("findAll{it.id>190}"); //groovy lang'e findAll(listedeki her seyi bul) ile baslariz //{it}===>item
        System.out.println("id List :" + idList);
        List<Integer> idList2 = json.getList("findAll{it.id>190}.id");//.id dersek sonuna sadece id'leri yazdirir
        System.out.println("idList2 sadece id= " + idList2);
        List<String> idList3 = json.getList("findAll{it.id==190}.title");//190 id linin title'ini alacagiz
        System.out.println("id 190'in title'i= " + idList3);
        //id'si 190'dan buyuk olan tum item'larin title'larini dondur
        //System.out.println(json.getList("findAll{it.id>190}.title"));
        //"Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        assertEquals(10, idList.size());
        //"Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        List<Integer> idListBuyukBes = json.getList("findAll{it.id<5}.userId");
        //"Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        assertEquals(4, idListBuyukBes.size());
        //4)"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);
        // System.out.println(json.getList("fidAll{it.title=='delectus aut autem'}")); // ---> title belitilen json datayı getirir
        // "delectus aut autem" başlığının id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
        //yukarida titeList adi altinda 5 den kucuk olanlari buldum zaten o yuzden sadece assertion yapacagim
        assertTrue(titleList.contains("delectus aut autem"));
        System.out.println(json.getList("findAll{it.title=='delectus aut autem'}.id"));//bu extra bir bilgi
        //title 'delectus aut autem' olan verinin ad'sini getir diyoruz kisaca






    }
}
