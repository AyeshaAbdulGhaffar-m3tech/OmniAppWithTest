package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.action.ActionCreateRequest;
import pojo.action.ActionCreateResponse;
import pojo.action.ActionData;
import pojo.action.ActionDeleteResponse;
import pojo.action.ActionGetByIdResponse;
import pojo.action.ActionUpdateRequest;
import pojo.action.ActionUpdateResponse;
import utils.TokenManager;

public class ActionsAPITest extends BaseTest {

	int actionId = -1;

	@DataProvider(name = "createActionData")
	public Object[][] createActionData() {
		return new Object[][] {
				// Positive
				{ TestCredentials.Action_Name,
						TestCredentials.Success_Status, TestCredentials.StatusCode_OK,
						TestCredentials.Success_Message },
				// Negative - Duplicate
				{ TestCredentials.Action_Name,
						TestCredentials.Success_Error, TestCredentials.StatusCode_Forbidden,
						TestCredentials.Action_Duplicate_Message },

		};
	}

	// All Test cases related to create user
	@Test(dataProvider = "createActionData", priority = 1)
	public void TC01_CreateAction(String name,String status, int expectedStatusCode,
			String expectedMessage) throws Exception {

		test = BaseTest.extent.createTest("Create Action Test Cases");

		String token = TokenManager.getToken(); // get Token

		// Create Object and then set values
		ActionCreateRequest ActionCreateRequest = new ActionCreateRequest();

		ActionCreateRequest.setName(name);
		
		

		ActionCreateResponse response = given().spec(requestSpecification()).header("Authorization", token)
				.body(ActionCreateRequest).when().post(getGlobalValue("resourceAction")).then().extract().as(ActionCreateResponse.class);

		Assert.assertEquals(response.getStatus(), status);
		Assert.assertEquals(response.getMessage(), expectedMessage);
		Assert.assertEquals(response.getResponseCode(), expectedStatusCode);
	}

	// Get User and get Id of created user
	@Test(priority = 2)
	public void TC02_GetAction() throws Exception {

		test = BaseTest.extent.createTest("Get Data of all Actions");
		String token = TokenManager.getToken(); // get Token

		Response response = given().spec(requestSpecification()).header("Authorization", token).when().get(getGlobalValue("resourceAction"))
				.then().extract().response();

		// Response in variable
		String message = getJsonPath(response, "message");
		Assert.assertEquals(message, TestCredentials.Success_Get_Message);

		String status = getJsonPath(response, "status");
		Assert.assertEquals(status, TestCredentials.Success_Status);

		int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
		Assert.assertEquals(responseCode, TestCredentials.StatusCode_OK);

		// Get the list of users as POJOs
		List<ActionData> actions = response.jsonPath().getList("data.action", ActionData.class);

		// Extract ID of user with specific email

		for (ActionData action : actions) {
			if (action.getName().equalsIgnoreCase(TestCredentials.Action_Name)) {
				actionId = action.getId();
				break;
			}

		}
		System.out.println(actionId);

	}


	
	// Update Action name by iD
		@Test(priority = 3)
		public void TC03_UpdateActionByID() throws Exception {
			test = BaseTest.extent.createTest("Update Action data by ID");
			String token = TokenManager.getToken(); // get Token
			
			ActionUpdateRequest ActionUpdateRequest = new ActionUpdateRequest();
			System.out.println(actionId);
			ActionUpdateRequest.setId(actionId);
			ActionUpdateRequest.setName(TestCredentials.Action_Update_Name);
			
			

			ActionUpdateResponse updateResponse = given().spec(requestSpecification()).header("Authorization", token)
					.body(ActionUpdateRequest)
					.when().put(getGlobalValue("resourceAction")).then().extract()
					.as(ActionUpdateResponse.class);

			System.out.println(updateResponse.getStatus());
			System.out.println(updateResponse.getMessage());
			System.out.println(updateResponse.getResponseCode());
			Assert.assertEquals(updateResponse.getStatus(), TestCredentials.Success_Status);
			Assert.assertEquals(updateResponse.getMessage(), TestCredentials.Action_Update_Success_Message);
			Assert.assertEquals(updateResponse.getResponseCode(), TestCredentials.StatusCode_OK);

		}
		
		// Get action by ID and verify name updated or not
		@Test(priority = 4)
		public void TC04_GetActionByID() throws Exception {
			test = BaseTest.extent.createTest("GET Action data by ID");
			String token = TokenManager.getToken(); // get Token

			ActionGetByIdResponse response = given().spec(requestSpecification()).header("Authorization", token).when()
					.get(getGlobalValue("resourceAction")+ actionId + "").then().extract().as(ActionGetByIdResponse.class);

			Assert.assertEquals(response.getData().getAction().getName(), TestCredentials.Action_Update_Name);
		

		}


	// Delete User - Super Admin and Other
	@Test(priority = 5)
	public void TC05_DeleteActionByID() throws Exception {
		test = BaseTest.extent.createTest("Delete Action data by ID");
		String token = TokenManager.getToken(); // get Token

		System.out.println(actionId);
		ActionDeleteResponse deleteResponse = given().spec(requestSpecification()).header("Authorization", token)
				.when().delete(getGlobalValue("resourceAction") + actionId + "").then().extract()
				.as(ActionDeleteResponse.class);

		
		System.out.println(deleteResponse.getStatus());
		System.out.println(deleteResponse.getMessage());
		System.out.println(deleteResponse.getResponseCode());
		
		Assert.assertEquals(deleteResponse.getStatus(), TestCredentials.Success_Status);
		Assert.assertEquals(deleteResponse.getMessage(), TestCredentials.Success_Delete);
		Assert.assertEquals(deleteResponse.getResponseCode(), TestCredentials.StatusCode_OK);

	}

}
