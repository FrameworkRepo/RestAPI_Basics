package ResuableCode;

import io.restassured.path.json.JsonPath;

public class ParseStringToJson {

    public static JsonPath jsonPathMethod(String Response){

        JsonPath js = new JsonPath(Response);

        return js;
    }
}
