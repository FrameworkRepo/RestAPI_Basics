package ResuableCode;

import io.restassured.path.json.JsonPath;

public class ParseStringToJson {

    public static JsonPath jsonPathMethod(String getResponse){

        JsonPath js = new JsonPath(getResponse);

        return js;
    }
}
