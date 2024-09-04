package com.ra.test;

import com.ra.client.RestClient;
import com.ra.common.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class TestGetUsers {
    String baseUri = "https://gorest.co.in";
    String basePath = "/public/v2/users";
    //String contentType = "application/json";
    String token = "f351aa77f1c2f1756c9442fa9933e70486363a13842d5a34c526bec81d404265";
    // boolean log = true;
    @Test
    public void testGetAllUsers() {
        Response response = RestClient.doGET("JSON", baseUri, basePath, token, true);
        RestClient.prettyPrint(response);

        //*********** Validating the status code ************
        int actual = RestClient.getStatusCode(response);
        System.out.println("Status code: " + actual);
        Assert.assertEquals(actual, Constants.STATUS_CODE_200);
        System.out.println("Status code validated");

        //************* Validating Status Line************
        String StatusLine = RestClient.getStatusLine(response);
        System.out.println("Status line: " + RestClient.getStatusLine(response));
        Assert.assertTrue(StatusLine.contains(Constants.STATUS_LINE_200));
        System.out.println("Status line validated");

        //************* Validating the header value ************
        String headerValue = RestClient.getHeaderValuer(response, "server");
        System.out.println("Header value: " + headerValue);
        Assert.assertTrue(headerValue.contains(Constants.HEADER_VALUE_SERVER));
        System.out.println("Header value validated for server");

    }
}
