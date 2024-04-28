package StaticJson;

import ResuableCode.ParseStringToJson;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StaticJsonPayloadToString {


    //    C:\Users\851101\Basics\AddPlace.json
    @Test
    public void test() throws IOException {
        String newAddress = "123 street, USA";

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\AddPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("status", equalTo("OK"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        //parse the json using jsonpath method

        String placeId = ParseStringToJson.jsonPathMethod(response).getString("place_id");

        System.out.println("Place_Id : " + placeId);
    }
}
