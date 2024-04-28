package Basics;

import File.Payload;
import ResuableCode.ParseStringToJson;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class check {

    @Test
    public void test(){
        String newAddress = "123 street, USA";

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("status",equalTo("OK"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        //parse the json using jsonpath method

        String placeId = ParseStringToJson.jsonPathMethod(response).getString("place_id");

        System.out.println("Place_Id : "+placeId);

        putCall(placeId,newAddress);

        getCall(placeId,newAddress);

        deleteCall(placeId);
    }
    public static void getCall(String placeId,String newAddress){

        String getResponse = given().queryParam("place_id",placeId).queryParam("key","qaclick123")
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        String address = ParseStringToJson.jsonPathMethod(getResponse).getString("address");

        Assert.assertEquals(newAddress,address);

    }

    public static void putCall(String placeId,String newAddress){


        String putResponse = given().queryParam("key","qaclick123")
                .body(Payload.putCall(placeId,newAddress))
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).extract().response().asString();


        String msg = ParseStringToJson.jsonPathMethod(putResponse).getString("msg");

        Assert.assertEquals("Address successfully updated",msg);    }

    public static void deleteCall(String placeId){

        String deleteResponse = given().body(Payload.deleteCall(placeId))
                .when().delete("maps/api/place/delete/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        String status = ParseStringToJson.jsonPathMethod(deleteResponse).getString("status");

        Assert.assertEquals("OK",status);
    }
}
