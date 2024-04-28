package Files;

import io.restassured.path.json.JsonPath;

public class Payload {

    public static String addPlace(){

        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": 2.383494,\n" +
                "    \"lng\": 8.427362\n" +
                "  },\n" +
                "  \"accuracy\": 90,\n" +
                "  \"name\": \"Front house\",\n" +
                "  \"phone_number\": \"(+91) 988 344 4321\",\n" +
                "  \"address\": \"21, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String putCall(String placeId,String newAddress){
        return"{\n" +
                "\"place_id\":\""+placeId+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n";
    }

    public static String deleteCall(String placeId){

        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }

    public static String ComplexJsonParse(){

        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";
    }
}
