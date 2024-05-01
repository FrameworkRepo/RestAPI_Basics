package SpecBuilders;

import POJO_Serialization.Addplace;
import POJO_Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestAndResponseSpecBuilders {

    @Test
    public void specBuilders(){

        //Common request and response will be create object for request/response specbuilders object
//        use that in request and response according.

//        RestAssured.baseURI="https://rahulshettyacademy.com";

//        Pojo_ Serialization - Converting Java object to Request Paylod

//        Created object for Pojo Main class
        Addplace ap = new Addplace();

//        Set value to the variables
        ap.setAccuracy(90);
        ap.setAddress("21, side layout, cohen 09");
        ap.setLanguage("French-IN");
        ap.setName("Front house");
        ap.setPhone_number("(+91) 988 344 4321");
        ap.setWebsite("http://google.com");
//created List of string to add it.
        List<String> type = new ArrayList<String>();

        type.add("shoe park");
        type.add("shop");
// pass the list object to set values to list of string variable
        ap.setTypes(type);
// created object for subclass of location (nested json)
        Location l = new Location();
        l.setLat(56.383494);
        l.setLng(13.427362);
//send the object to main class set variable
        ap.setLocation(l);

//Specs Builder
//        Make sure after adding all common code then build it.
       RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

       ResponseSpecification resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
               .expectStatusCode(200).build();
        //usual one
//        String response = given().log().all().spec(req)
//                .body(ap)
//                .when().post("maps/api/place/add/json")
//                .then().assertThat().statusCode(200).extract().response().asString();

//        Spec builder
        String response = given().log().all().spec(reqSpec)
                .body(ap)
                .when().post("maps/api/place/add/json")
                .then().spec(resSpec).extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);

        System.out.println(js.getString("scope"));

    }
}
