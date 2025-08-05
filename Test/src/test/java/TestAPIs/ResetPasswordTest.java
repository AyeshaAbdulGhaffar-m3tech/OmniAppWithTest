package TestAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.LoginRequest;
import pojo.LogoutResponse;
import pojo.ResetPasswordRequest;
import utils.TestDataUtil;
import utils.TokenManager;

public class ResetPasswordTest extends BaseTest {
	
	@Test (dataProvider = "resetPasswordScenarios")
    public void TC01_ResetPassword_correctPassword(String currentPassword,String newPassword, int expectedResponseCode, String expectedMessage) throws Exception {
        //String token = loginAndGetToken(TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD); // From Base Test
		String token=TokenManager.getToken(); //get Token
        ResetPasswordRequest resetPasswordRequest = TestDataUtil.getResetPasswordRequest(currentPassword, newPassword);

        
        Response response = given()
        		.spec(requestSpecification())
                .header("Authorization",  token)
                .body(resetPasswordRequest)
                .when()
                .post(getGlobalValue("resourceResetpassword")) // Update with your actual logout endpoint if needed
                .then()
                .statusCode(expectedResponseCode)
                .extract()
                .response();

     // Validations
        String message = getJsonPath(response, "message");
        String status = getJsonPath(response, "status");
        int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));

        //Assert.assertEquals(status, "success", "Status mismatch");
        Assert.assertEquals(message, expectedMessage, "Message mismatch");
        Assert.assertEquals(responseCode, expectedResponseCode, "Response code mismatch");
    }
	
    @DataProvider(name = "resetPasswordScenarios")
    public Object[][] resetPasswordScenarios() {
        return new Object[][]{
            {TestCredentials.VALID_PASSWORD, TestCredentials.NEW_PASSWORD, 200, "Password updated successfully"},
            {TestCredentials.INVALID_PASSWORD, TestCredentials.NEW_PASSWORD, 400, "Current password is incorrect"},
            //Again revert password
            {TestCredentials.NEW_PASSWORD, TestCredentials.VALID_PASSWORD, 200, "Password updated successfully"},
        };
    }

}
