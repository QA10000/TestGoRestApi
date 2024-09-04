package com.ra.client;


import java.util.List;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class RestClient {

    //*********** HTTP Methods************

    public static Response doGET(String contentType, String baseURI, String basePath, String token, boolean log) {

        RestClient.setBaseUrl(baseURI);
        RequestSpecification request = RestClient.createRequest(contentType, token, log);
        return RestClient.getResponse("GET",request,basePath);


    }

    private static void doGET(String ContentType, String baseURI, String basePath, String token) {

    }


    public static void doPOST() {

    }

    public static void doPUT() {

    }

    public static void doDELETE() {

    }

    /*
     * Set the base URI
     * @param baseUri
     */
    public static void setBaseUrl(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    /*
   @param content-type
   @param token
   @param log
    @return RequestSpecification
     */
    public static RequestSpecification createRequest(String contentType, String token, boolean log) {
        RequestSpecification request;
        if (log) {
            request = RestAssured.given().log().all();
        } else {
            request = RestAssured.given();
        }

        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        if (contentType.equalsIgnoreCase("JSON")) {
            request.contentType(ContentType.JSON);
        } else if (contentType.equalsIgnoreCase("XML")) {
            request.contentType(ContentType.XML);
        } else if (contentType.equalsIgnoreCase("TEXT")) {
            request.contentType(ContentType.TEXT);

        }
        return request;
    }

    /* @param httpMethod
     * @param request
     * @param basePath
     * @return Response
     */

    public static Response executeRequest(String httpMethod, RequestSpecification request, String basePath) {
        Response response = null;

        switch (httpMethod) {
            case "GET":
                response = request.get(basePath);
                break;
            case "POST":
                response = request.post(basePath);
                break;
            case "PUT":
                response = request.put(basePath);
                break;
            case "DELETE":
                response = request.delete(basePath);
                break;
            default:
                System.out.println("Invalid method type");
                break;
        }
        return response;
    }
/*


@return Response
 */


    public static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
        return executeRequest(httpMethod, request, basePath);

    }

    //public static void prettyPrintResponse(Response response) {
    public static void prettyPrint(Response response) {
        if (response != null) {
            response.prettyPrint();
        } else {
            System.out.println("Response is null");
        }
    }


    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getStatusLine(Response response) {
        return response.getStatusLine();
    }

    public static JsonPath getJsonPath(Response response) {
        return response.jsonPath();
    }


    public static String getHeaderValuer(Response response, String headerKey) {
        return response.getHeader(headerKey);
    }

    public static int getHeadersCount(Response response) {
        return response.getHeaders().size();
    }


    public static List<Header> getResponseHeaders(Response response) {
        Headers header = response.getHeaders();
        List<Header> headerList = header.asList();
        return headerList;

    }
}


