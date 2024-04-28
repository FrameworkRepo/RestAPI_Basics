package DynamicJson;

import File.Payload;
import ResuableCode.ParseStringToJson;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJsonConcept {

    @Test(dataProvider="BooksData")
    public void addBook(String isbn,String aisle){

        RestAssured.baseURI = "http://216.10.245.166";

       String response = given().header("Conten-Type","application/json")
               .body(Payload.addBook(isbn,aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        String id = ParseStringToJson.jsonPathMethod(response).getString("ID");
        System.out.println(id);
    }
    @DataProvider(name="BooksData")
    public Object[][] data(){

        Object[][] obj = new Object[][]{
                {"lkjh","098"},
                {"nhgv","297"},
                {"outl","310"}
        };
        return obj;
    }
}
