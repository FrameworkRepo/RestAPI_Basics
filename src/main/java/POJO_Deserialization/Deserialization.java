package POJO_Deserialization;

import ResuableCode.ParseStringToJson;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class Deserialization {

   String  url = "https://rahulshettyacademy.com";
   //Array of String
   String[] courseNames = {"Selenium Webdriver Java","Cypress","Protractor"};
    @Test
    public void pojoClass(){
        RestAssured.baseURI= url;

        String accessTokenResponse = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().post("oauthapi/oauth2/resourceOwner/token")
                .then().extract().response().asString();

        String access_token = ParseStringToJson.jsonPathMethod(accessTokenResponse).getString("access_token");
        System.out.println(given().queryParam("access_token",access_token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .then().log().all());

        GetCourseDetails courseDetails = given().queryParam("access_token",access_token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourseDetails.class);

        //Get data from Pojo class using above object to add values.
        GetCourseDetails courses = new GetCourseDetails();

        System.out.println(courseDetails.getInstructor());

        //  To set data in payload via object created for GetCourseDetails Pojo class.
        courseDetails.setInstructor("Naveen");

        System.out.println(courseDetails.getInstructor());

        System.out.println(courseDetails.getCourses().getApi().get(1).getCourseTitle());

        List<ListofElementsForCourses> infoOfCourses = courseDetails.getCourses().getApi();

        for(int i=0; i<infoOfCourses.size();i++){

            if(infoOfCourses.get(i).getCourseTitle().contains("SoapUI Webservice")){
                System.out.println(infoOfCourses.get(i).getPrice());
                break;
            }
        }
        List<ListofElementsForCourses> allCourses = courseDetails.getCourses().getWebAutomation();

        //Fetching all details and storing into one list to compare and see
        ArrayList<String> actual=new ArrayList<String>();
        for (int i=0;i<allCourses.size();i++) {
            System.out.println(allCourses.get(i).getCourseTitle());
            actual.add(allCourses.get(i).getCourseTitle());
        }

        //convert array of string into List of array

        List<String> expected =Arrays.asList(courseNames);

        //compare actual with expcted course details
        Assert.assertTrue(actual.equals(expected));
    }
}
