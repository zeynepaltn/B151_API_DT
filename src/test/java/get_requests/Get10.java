package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olmalı)
     */

    @Test
    public void get10() {
        // Set the URL
        spec.pathParam("first", "users");

        // Set the expected data

        // Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        // Do assertion
        response
                .then()
                .body("meta.pagination.limit", equalTo(10))
                .body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data", hasSize(10))
                .body("data.status", hasItem("active"))
                .body("data.name", hasItems("Raj Singh JD", "Bilwa Pillai", "Damodara Tandon"));
                //body("data.findAll{it.gender=='female'}.size()",lessThanOrEqualTo("data.findAll{it.gender=='female'}.size()")); ////hamcrest matchers icine groovy methodlari yazilmiyor
        JsonPath json=response.jsonPath();
        List<String> femalelist=json.getList("data.findAll{it.gender=='female'}");
        System.out.println("femalelist.size() = " + femalelist.size());
        List<String> malelist=json.getList("data.findAll{it.gender=='male'}");
        System.out.println("malelist.size() = " + malelist.size());


        //assertTrue() methodu icerisinde Hamcrest matchers methodlari kullanilmaz
        assertTrue(malelist.size()<=femalelist.size());
        //assertThat() methodu icerisinde Hamcrest Matchers methodlari kullanilabilir
        assertThat(femalelist.size(),greaterThanOrEqualTo(malelist.size()));


    }
}