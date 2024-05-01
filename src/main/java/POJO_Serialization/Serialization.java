package POJO_Serialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

    @Test
    public void serialization(){

        RestAssured.baseURI="https://rahulshettyacademy.com";

        Addplace ap = new Addplace();

        ap.setAccuracy(90);
        ap.setAddress("21, side layout, cohen 09");
        ap.setLanguage("French-IN");
        ap.setName("Front house");
        ap.setPhone_number("(+91) 988 344 4321");
        ap.setWebsite("http://google.com");

        List<String> type = new ArrayList<String>();

        type.add("shoe park");
        type.add("shop");

        ap.setTypes(type);

        Location l = new Location();
        l.setLat(56.383494);
        l.setLng(13.427362);

        ap.setLocation(l);


        String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(ap)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();


        System.out.println(response);
    }

}
