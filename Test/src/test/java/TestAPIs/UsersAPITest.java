package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.user.UserCreateRequest;
import pojo.user.UserCreateResponse;
import pojo.user.UserData;
import pojo.user.UserDeleteResponse;
import pojo.user.UserGetByIdResponse;
import utils.TokenManager;

public class UsersAPITest extends BaseTest {
	
	 int userId = -1;
	 int SuperUserID = -1;
	
	 @DataProvider(name = "createUserData")
	    public Object[][] createUserData() {
	        return new Object[][]{
	                // Positive
	                {TestCredentials.User_Email, TestCredentials.User_Name, TestCredentials.User_UserName, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_OK,TestCredentials.Success_Message },
	                // Negative - missing email
	                {TestCredentials.EMPTY_EMAIL, TestCredentials.User_Name, TestCredentials.User_UserName, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_BadRequest,TestCredentials.User_Invalid_Body },
	                // Invalid email
	                {TestCredentials.INVALID_EMAIL, TestCredentials.User_Name, TestCredentials.User_UserName, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_BadRequest,TestCredentials.User_Invalid_Body },
	                // Duplicate email
	                {TestCredentials.User_Email, TestCredentials.User_Name_N, TestCredentials.User_UserName_N, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_BadRequest,TestCredentials.User_DuplicateEmail_Message },
	             // Duplicate Name
	                {TestCredentials.User_Email_N, TestCredentials.User_Name, TestCredentials.User_UserName, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_Forbidden,TestCredentials.User_DuplicateName_Message },
	             // Duplicate User Name
	                {TestCredentials.User_Email_N, TestCredentials.User_Name_N, TestCredentials.User_UserName, TestCredentials.User_Contact, TestCredentials.User_RoleId, TestCredentials.UserPassword, TestCredentials.StatusCode_BadRequest,TestCredentials.User_DuplicateUserName_Message },
	                
	         };
	    }
	 


	 //All Test cases related to create user
	@Test (dataProvider = "createUserData" , priority = 1)
	public void TC01_CreateUser(String email, String name, String userName, String contact, Integer roleId, String password, int expectedStatusCode, String expectedMessage) throws Exception {
		
		 test = BaseTest.extent.createTest("Create User Test");
		 
		String token=TokenManager.getToken(); //get Token
		
		//Create Object and then set values
		UserCreateRequest UserCreateRequest = new UserCreateRequest();
		
		UserCreateRequest.setEmail(email);
		UserCreateRequest.setName(name);
		UserCreateRequest.setUserName(userName);
		UserCreateRequest.setContact(contact);
		UserCreateRequest.setRoleId(roleId);
		UserCreateRequest.setPassword(password);
		
		UserCreateResponse response =given()
		.spec(requestSpecification())
		.header("Authorization", token)
		.body(UserCreateRequest)
		.when().post(getGlobalValue("resourceUser")).then()
		.extract().as(UserCreateResponse.class);
		

        //Assert.assertEquals(response.getStatus(), "success");
        Assert.assertEquals(response.getMessage(), expectedMessage);
        Assert.assertEquals(response.getResponseCode(), expectedStatusCode);
	}
	
	
	
	//Get User and get Id of created user
	@Test (priority=2)
	public void TC02_GetUser() throws Exception {
		
		 test = BaseTest.extent.createTest("Get Data of all Users");
		String token=TokenManager.getToken(); //get Token
		
		
		Response response =given()
		.spec(requestSpecification())
		.header("Authorization", token)
		.when().get(getGlobalValue("resourceUser")).then()
		.extract().response();
		
		//Response in variable
		 String message = getJsonPath(response, "message");
		 Assert.assertEquals(message, "Successfully Get");
		 
		 String status = getJsonPath(response, "status");
		 Assert.assertEquals(status, "success");
		 
		 int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
		  Assert.assertEquals(responseCode, 200);


	      // Get the list of users as POJOs
	      List<UserData> users = response.jsonPath().getList("data.user", UserData.class);

	      // Extract ID of user with specific email
	     

	      for (UserData user : users) {
	    	  
	    	  System.out.println("User: " + user.getEmail() + " | RoleID: " + user.getRoleId());
	    	  
	    	 // System.out.println(user.getEmail());
	          if (user.getEmail().equalsIgnoreCase(TestCredentials.User_Email) && user.getRoleId() != 1) {
	              userId = user.getId();
	              //break;
	          }
	          
	          // Get ID based on roleId = 1
	          if (user.getRoleId() == 1) {
	        	  SuperUserID = user.getId();
	          }
	          
	      }

	   // Validate both IDs found
	      Assert.assertTrue(userId != -1, "User not found with email: " + TestCredentials.User_Email);
	      Assert.assertTrue(SuperUserID != -1, "User with roleId 1 not found");
	      
	     // Assert.assertTrue(userId != -1, "User not found with email: " + TestCredentials.User_Email);
	     // System.out.println("Extracted userId: " + userId);
	}
	
	
	
	//Get User and get Id of created user
		@Test (priority=3)
		public void TC03_GetUserByID() throws Exception {
			 test = BaseTest.extent.createTest("GET User data by ID");
			String token=TokenManager.getToken(); //get Token
			
			
			UserGetByIdResponse response =given()
			.spec(requestSpecification())
			.header("Authorization", token)
			.when().get(getGlobalValue("resourceUser")+userId+"").then()
			.extract().as(UserGetByIdResponse.class);
			
			 Assert.assertEquals(response.getData().getUser().getEmail(), TestCredentials.User_Email);
			 Assert.assertEquals(response.getData().getUser().getUserName(), TestCredentials.User_UserName);
			 Assert.assertEquals(response.getData().getUser().getName(), TestCredentials.User_Name);
			 
		}
		
		
		//Delete User - Super Admin and Other
		@Test (priority = 4)
		public void TC04_DeleteUserByID() throws Exception {
			test = BaseTest.extent.createTest("Delete User data by ID");
			String token=TokenManager.getToken(); //get Token
			
			
			UserDeleteResponse regularUserResponse =given()
			.spec(requestSpecification())
			.header("Authorization", token)
			.when().delete(getGlobalValue("resourceUser")+userId+"").then()
			.extract().as(UserDeleteResponse.class);
			
			 Assert.assertEquals(regularUserResponse.getStatus(), TestCredentials.Success_Status );
			 Assert.assertEquals(regularUserResponse.getMessage(), TestCredentials.User_Success_Delete );
			 Assert.assertEquals(regularUserResponse.getResponseCode(), TestCredentials.StatusCode_OK);
			 
			 
			 //Delete Super Admin
			 UserDeleteResponse SuperAdminResponse =given()
						.spec(requestSpecification())
						.header("Authorization", token)
						.when().delete("/user/"+SuperUserID+"").then()
						.extract().as(UserDeleteResponse.class);
						
						 Assert.assertEquals(SuperAdminResponse.getStatus(), TestCredentials.Success_Error );
						 Assert.assertEquals(SuperAdminResponse.getMessage(), TestCredentials.Delete_SuperAdmin_Message );
						 Assert.assertEquals(SuperAdminResponse.getResponseCode(), TestCredentials.StatusCode_BadRequest);
			 
		}
		
		
	
	
	
	
}

