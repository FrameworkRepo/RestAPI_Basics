package OAuth;

import ResuableCode.ParseStringToJson;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class ClientCredentials {

    @Test
    public void oauth_ClientCredentials(){

        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String oauthtoken_response = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().post("oauthapi/oauth2/resourceOwner/token")
                .then().assertThat().statusCode(200).extract().response().asString();

        String access_token = ParseStringToJson.jsonPathMethod(oauthtoken_response).getString("access_token");

       String getCourseDetails =  given().queryParam("access_token",access_token)
                .when().get("oauthapi/getCourseDetails")
                .then().log().all().extract().response().asString();



    }
}
