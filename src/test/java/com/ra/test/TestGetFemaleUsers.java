package com.ra.test;

import com.ra.client.RestClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestGetFemaleUsers {
    String baseUri = "https://gorest.co.in";
    String basePath = "/public/v1/users?gender=female";
    String token = "f351aa77f1c2f1756c9442fa9933e70486363a13842d5a34c526bec81d404265";

    @Test
    public void getFemaleUsers() {
        Response response = RestClient.doGET("JSON", baseUri, basePath, token, true);
        RestClient.prettyPrint(response);
        // *********** Validating the JSON response ************
        JsonPath jsonPath = RestClient.getJsonPath(response);
        Object limitValue = jsonPath.get("meta.pagination.limit");
        Assert.assertEquals(limitValue, 10);
        System.out.println("Page Limit value validated successfully");

        //*********** Validating Status Inactive************
        ArrayList<Object> responseValues = jsonPath.get("data.gender");
        System.out.println("Response values: " + responseValues);
        Assert.assertTrue(responseValues.contains("female"));
        System.out.println("Status validated successfully");
        //************Program ended successfully************
        System.out.println("Program ended successfully");
    }
}
