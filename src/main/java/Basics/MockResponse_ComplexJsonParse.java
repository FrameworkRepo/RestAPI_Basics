package Basics;

import File.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MockResponse_ComplexJsonParse {

    @Test
    public void complexJsonParse() {
        JsonPath js = new JsonPath(Payload.ComplexJsonParse());
        //Store course size into a variable
        int countSize = js.getInt("courses.size()");
        //Store purchase amount into a variable
        String totalAmount = js.getString("dashboard.purchaseAmount");
        //print title of first title
        System.out.println(js.getString("courses[0].title"));
        //Print all course title and respective price
        for (int i = 0; i < countSize; i++) {
            System.out.print(js.getString("courses[" + i + "].title") + " : ");
            System.out.println(js.get("courses[" + i + "].price").toString());
        }
        //Print no of copies sold in RPA course
        for (int i = 0; i < countSize; i++) {
            String courseName = js.getString("courses[" + i + "].title");

            if (courseName.equalsIgnoreCase("cypress")) {
                int amount = js.getInt("courses[" + i + "].price");
                int copies = js.getInt("courses[" + i + "].copies");

                System.out.println("copies : " + copies);
//                Sold amount
                System.out.println("Sold : " + amount * copies);
                break;
            }

        }

//        Verify if sum of all course price matches with purchase Amount
        int totalPurchasedAmount = js.get("dashboard.purchaseAmount");

        int sold = 0;
        for (int i = 0; i < countSize; i++) {
            String courseName = js.getString("courses[" + i + "].title");

                int amount = js.getInt("courses[" + i + "].price");
                int copies = js.getInt("courses[" + i + "].copies");
                int tempVar = amount * copies;
            sold+=tempVar;

            System.out.println("copies : " + copies);
//                Sold amount

        }
        System.out.println("Sold : " + sold);

        Assert.assertEquals(totalPurchasedAmount,sold);
        System.out.println("Amount matched");

        /*if(totalPurchasedAmount == sold){
            System.out.println("Amount matched");
        }*/

    }
}
