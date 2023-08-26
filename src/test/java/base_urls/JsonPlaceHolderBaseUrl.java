package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    protected RequestSpecification spec; //protected yaptik baska folder'lardan da cagirabilmek icin
    @Before
    public void Setup(){
        spec=new RequestSpecBuilder().
                setContentType(ContentType.JSON).//hangi turde veri gonderiyoruz veri tabanina onu belirtiyoruz
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();

    }
    //tekrarli methodlarda tekrarli olarak kullanilan degerler burada yazilir
}
/*
API Testlerinde Base URL Kullanımı ve Faydaları
    Bir API testini yazarken, sıklıkla aynı temel URL'yi (Base URL) kullanmamız gerekebilir.
    Base URL, API servisimize ulaşmak için kullanılan temel adresi temsil eder.
    Bu durumda, aynı Base URL'yi her test metodu içinde tekrar tekrar belirtmek yerine, kodunuzun daha düzenli,
    okunabilir ve yönetilebilir olması için "Base URL" olarak adlandırdığımız bir yapı oluşturabiliriz.

Bu Yapının Faydaları:
    Daha Az Tekrarlama: Her test metodu içinde aynı Base URL'yi yazmak yerine, bu yapıyı kullanarak
    sadece bir kez tanımlarsınız. Bu da kodunuzu daha az tekrarlamalı hale getirir ve bakımı kolaylaştırır.

    Daha Düzenli ve Okunabilir Kod: Test metotlarınızın içeriği daha temiz ve okunabilir olur çünkü her seferinde
    Base URL'yi düşünmek zorunda kalmazsınız.

    Değişiklik Yönetimi: Eğer Base URL değişirse, sadece bu yapıyı güncellemek yeterlidir.
    Tüm test metotlarını tek tek değiştirmek zorunda kalmazsınız.

Bu metodun içerisinde kullanılabilecek metotlar:
    setAccept(), setContentType(), setAuth() gibi..
 */