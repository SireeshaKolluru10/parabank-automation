package com.parabank.utilities;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtil {

	private static RequestSpecification getBaseRequest() {
	    RestAssuredConfig config = RestAssuredConfig.config()
	        .httpClient(HttpClientConfig.httpClientConfig()
	            .setParam("http.connection.timeout", 10000)
	            .setParam("http.socket.timeout", 10000));

	    return RestAssured
	        .given()
	        .config(config)
	        .baseUri(Constants.API_BASE_URL)
	        .header("Content-Type", "application/json")
	        .header("Accept", "application/json");
	}

    // GET request
    public static Response get(String endpoint) {
        return getBaseRequest()
            .when()
            .get(endpoint)
            .then()
            .extract()
            .response();
    }

    // POST request with body
    public static Response post(String endpoint, Object body) {
        return getBaseRequest()
            .body(body)
            .when()
            .post(endpoint)
            .then()
            .extract()
            .response();
    }

    // POST request without body
    public static Response post(String endpoint) {
        return getBaseRequest()
            .when()
            .post(endpoint)
            .then()
            .extract()
            .response();
    }

    // PUT request
    public static Response put(String endpoint, Object body) {
        return getBaseRequest()
            .body(body)
            .when()
            .put(endpoint)
            .then()
            .extract()
            .response();
    }

    // DELETE request
    public static Response delete(String endpoint) {
        return getBaseRequest()
            .when()
            .delete(endpoint)
            .then()
            .extract()
            .response();
    }

    // Validate status code
    public static void assertStatusCode(Response response,
                                        int expectedCode) {
        int actualCode = response.getStatusCode();
        if (actualCode != expectedCode) {
            throw new AssertionError(
                "Expected status: " + expectedCode +
                " but got: " + actualCode);
        }
    }

    // Get value from JSON response
    public static String getJsonValue(Response response,
                                      String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }
}