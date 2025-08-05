package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.LogoutResponse;

import static io.restassured.RestAssured.given;

public class LogoutTest extends BaseTest {
	
	@Test
    public void TC01_Logout_ValidToken() throws Exception {
        String token = loginAndGetToken(TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD); // From Base Test

        System.out.println("Using Token: " + token);
        Response response = given()
        		.spec(requestSpecification())
                .header("Authorization",  token)
                .when()
                .post(getGlobalValue("resourceLogout")) // Update with your actual logout endpoint if needed
                .then()
                .statusCode(200)
                .extract()
                .response();

        LogoutResponse logoutResponse = response.as(LogoutResponse.class);

        Assert.assertEquals(logoutResponse.getStatus(), TestCredentials.Success_Status, "Status mismatch");
        Assert.assertEquals(logoutResponse.getMessage(), TestCredentials.Success_Logout , "Message mismatch");
        Assert.assertEquals(logoutResponse.getResponseCode(), TestCredentials.StatusCode_OK, "Response code mismatch");
    }

}
