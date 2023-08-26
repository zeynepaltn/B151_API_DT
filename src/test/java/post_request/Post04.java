package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends HerokuAppBaseUrl {
    /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    @Test
    public void post04() {
        //Set Url
        spec.pathParam("first","booking");
        //Set the Expected Data
        //Once datamizin ic kismini olustururz
        BookingDatesPojo bookingDates=new BookingDatesPojo("2021-09-21","2021-12-21");
        //Sonra da dis kisimini olustururuz
        BookingPojo expectedData=new BookingPojo("Ali","Can",999,true, bookingDates,"Breakfast");
        System.out.println("expected Data = " + expectedData); //Bookingpojo class'inda toString metodu zateb var o yuzden bize string olarak yazdiracak
                                                                //Bir daha toString dememize gerek yok
        //Send the request get the response
        Response response=given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //Do Assertions
        BookingResponsePojo actualData=response.as(BookingResponsePojo.class); //Hangi veri tipine donusturulmesini istiyorsak o class'in adini yaziypruz
        System.out.println("actual Data = " + actualData);

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }
}

//response'da kac scope varsa o kadar pojo olmali. Burada 3 tane vardi.
// BookingResponsePojo'yu istedigimiz sirada olusturabiliriz.
// Ancak ona expected data kisminda yer yok. En buyuk olan hepsini kapsayan pojoyu
//Do assertion icine yaziyoruz.
