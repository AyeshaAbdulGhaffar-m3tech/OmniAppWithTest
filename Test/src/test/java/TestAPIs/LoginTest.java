package TestAPIs;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.LoginData;
import pojo.LoginRequest;
import pojo.LoginResponse;
import utils.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginTest extends BaseTest {
	//LoginRequest request = new LoginRequest(String, String);
	ObjectMapper objectMapper = new ObjectMapper();  // ✅ define at class level
	LoginResponse loginResponse = new LoginResponse();

	 @Test (dataProvider = "loginScenarios")
	    public void testLogin(String email, String password, int expectedResponseCode, String expectedMessage) throws IOException {
	         //request = new LoginRequest("super@gamil.com", "super111");
	         LoginRequest request = TestDataUtil.getLoginRequest(email, password);

	          Response response = RestAssured.given()
	        		 .spec(requestSpecification())
	            .body(request)
	        .when()
	            .post(getGlobalValue("resourceLogin"));
	          
	           loginResponse = response.as(LoginResponse.class);
//	        .then()
//	            .statusCode(expectedResponseCode)
//	            .extract()
//	            .as(LoginResponse.class);

	        // Assert response fields
	        //Assert.assertEquals(response.getStatus(), "success");
	        Assert.assertEquals(loginResponse.getMessage(), expectedMessage);
	        Assert.assertEquals(loginResponse.getResponseCode(), expectedResponseCode);
	       // Assert.assertEquals(response.getData().getEmail(), "super@gamil.com");
	        
	        if (expectedResponseCode == 200 && loginResponse.getData() != null && loginResponse.getData().isObject()) {
	            // Convert to LoginData if needed
	            LoginData data = objectMapper.treeToValue(loginResponse.getData(), LoginData.class);
	            System.out.println("Token: " + data.getToken());
	            Assert.assertNotNull(data.getToken());
	        } else {
	            System.out.println("No valid data present or login failed.");
	        }
	        
	    }


	    @DataProvider(name = "loginScenarios")
	    public Object[][] loginScenarios() {
	        return new Object[][]{
	            {TestCredentials.VALID_EMAIL, TestCredentials.VALID_PASSWORD, TestCredentials.StatusCode_OK, TestCredentials.Success_Login},
	            {TestCredentials.VALID_EMAIL, TestCredentials.INVALID_PASSWORD, TestCredentials.StatusCode_Unauthorized, TestCredentials.Invalid_Email_Password},
	            {TestCredentials.EMPTY_EMAIL, TestCredentials.VALID_PASSWORD, TestCredentials.StatusCode_BadRequest, TestCredentials.User_Invalid_Body},
	            {TestCredentials.VALID_EMAIL, TestCredentials.EMPTY_PASSWORD, TestCredentials.StatusCode_BadRequest, TestCredentials.User_Invalid_Body},
	            {TestCredentials.INVALID_EMAIL, TestCredentials.VALID_PASSWORD, TestCredentials.StatusCode_BadRequest, TestCredentials.User_Invalid_Body},
	        };
	    }
	    //password is required!
	    //email is required!
	    
}
