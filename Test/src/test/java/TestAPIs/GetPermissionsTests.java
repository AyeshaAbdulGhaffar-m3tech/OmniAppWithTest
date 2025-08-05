package TestAPIs;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetPermissionsTests extends BaseTest {
	
    // TC02 - TC04: Invalid Tokens
    @DataProvider(name = "invalidTokens")
    public Object[][] getInvalidTokens() {
        return new Object[][]{
                {"Bearer invalid-token"},
                {"Bearer "},
                {""}
        };
    }

    // TC01: Valid Token
    @Test(priority = 1)
    public void TC01_GetPermissions_ValidToken() throws Exception {
        String token = loginAndGetToken(TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD);
        
        Response response = given()
                .spec(requestSpecification())
                .header("Authorization", token)
                .when()
                .get(getGlobalValue("resourcePermission"))
                .then()
                .extract()
                .response();

        // Validations
        String message = getJsonPath(response, "message");
        int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseCode, 200);
        Assert.assertEquals(message, "Role fetched Successfully");

        // Optional: check permission data is present
        List<Map<String, Object>> permissions = response.jsonPath().getList("data.permissions");
        Assert.assertNotNull(permissions, "Permissions list is null");
        Assert.assertTrue(permissions.size() > 0, "Permissions list is empty");
    }



    @Test(dataProvider = "invalidTokens", priority = 2)
    public void TC02_GetPermissions_InvalidTokens(String token) throws Exception {
        Response response = given()
                .spec(requestSpecification())
                .header("Authorization", token)
                .when()
                .get("/auth/permissions")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 401 || statusCode == 403,
                "Expected 401/403, but got: " + statusCode);
    }

//    // TC06: Empty Permissions
//    @Test(priority = 3)
//    public void TC06_GetPermissions_EmptyPermissionsList() throws Exception {
//        String token =  loginAndGetToken(TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD);
//
//        Response response = given()
//                .spec(requestSpecification())
//                .header("Authorization", token)
//                .when()
//                .get("/auth/permissions")
//                .then()
//                .extract()
//                .response();
//
//        Assert.assertEquals(response.statusCode(), 200);
//        List<Map<String, Object>> permissions = response.jsonPath().getList("data.permissions");
//        Assert.assertTrue(permissions.isEmpty(), "Expected empty permissions list");
//    }

    // TC07: Response Time Check
    @Test(priority = 4)
    public void TC07_GetPermissions_ResponseTimeCheck() throws Exception {
        String token =  loginAndGetToken(TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD);

        given()
                .spec(requestSpecification())
                .header("Authorization", token)
                .when()
                .get("/auth/permissions")
                .then()
                .time(lessThan(2000L));
    }
}
