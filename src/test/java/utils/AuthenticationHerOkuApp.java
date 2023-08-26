package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {
    public static String generateToken() {

        String body = "{\"username\" : \"admin\",\"password\" : \"password123\"}";

        Response response = given(). //bir post request gerceklestirecegiz, bilgileri girdik
                body(body).
                contentType(ContentType.JSON).
                when().
                post("https://restful-booker.herokuapp.com/auth");

        return response.jsonPath().getString("token");
    }

}
