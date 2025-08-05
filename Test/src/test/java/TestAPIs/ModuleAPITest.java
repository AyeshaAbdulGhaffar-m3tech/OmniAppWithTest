package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.module.ModuleCreateRequest;
import pojo.module.ModuleCreateResponse;
import pojo.module.ModuleData;
import pojo.module.ModuleDeleteResponse;
import pojo.module.ModuleGetByIdResponse;
import utils.TokenManager;

public class ModuleAPITest extends BaseTest {

	int moduleId = -1;

	@DataProvider(name = "createModuleData")
	public Object[][] createModuleData() {
		return new Object[][] {
				// Positive
				{ TestCredentials.Module_Name, TestCredentials.Module_Path, TestCredentials.Module_Icon,
						TestCredentials.Success_Status, TestCredentials.StatusCode_OK,
						TestCredentials.Success_Message },
				// Negative - Duplicate
				{ TestCredentials.Module_Name, TestCredentials.Module_Path, TestCredentials.Module_Icon,
						TestCredentials.Success_Error, TestCredentials.StatusCode_BadRequest,
						TestCredentials.Module_Duplicate_Message },

		};
	}

	// All Test cases related to create user
	@Test(dataProvider = "createModuleData", priority = 1)
	public void TC01_CreateModule(String name, String path, String icon, String status, int expectedStatusCode,
			String expectedMessage) throws Exception {

		test = BaseTest.extent.createTest("Create Module Test Cases");

		String token = TokenManager.getToken(); // get Token

		// Create Object and then set values
		ModuleCreateRequest ModuleCreateRequest = new ModuleCreateRequest();

		ModuleCreateRequest.setName(name);
		;
		ModuleCreateRequest.setPath(path);
		;
		ModuleCreateRequest.setIcon(icon);
		;

		ModuleCreateResponse response = given().spec(requestSpecification()).header("Authorization", token)
				.body(ModuleCreateRequest).when().post(getGlobalValue("resourceModule")).then().extract().as(ModuleCreateResponse.class);

		Assert.assertEquals(response.getStatus(), status);
		Assert.assertEquals(response.getMessage(), expectedMessage);
		Assert.assertEquals(response.getResponseCode(), expectedStatusCode);
	}

	// Get User and get Id of created user
	@Test(priority = 2)
	public void TC02_GetModule() throws Exception {

		test = BaseTest.extent.createTest("Get Data of all MOdules");
		String token = TokenManager.getToken(); // get Token

		Response response = given().spec(requestSpecification()).header("Authorization", token).when().get(getGlobalValue("resourceModule"))
				.then().extract().response();

		// Response in variable
		String message = getJsonPath(response, "message");
		Assert.assertEquals(message, "Successfully Get");

		String status = getJsonPath(response, "status");
		Assert.assertEquals(status, TestCredentials.Success_Status);

		int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
		Assert.assertEquals(responseCode, TestCredentials.StatusCode_OK);

		// Get the list of users as POJOs
		List<ModuleData> modules = response.jsonPath().getList("data.module", ModuleData.class);

		// Extract ID of user with specific email

		for (ModuleData module : modules) {

			// System.out.println("User: " + module.get() + " | RoleID: " +
			// module.getRoleId());

			// System.out.println(module.getEmail());
			if (module.getName().equalsIgnoreCase(TestCredentials.Module_Name)
					&& module.getPath().equalsIgnoreCase(TestCredentials.Module_Path)) {
				moduleId = module.getId();
				break;
			}

		}

	}

	// Get User and get Id of created user
	@Test(priority = 3)
	public void TC03_GetModuleByID() throws Exception {
		test = BaseTest.extent.createTest("GET Module data by ID");
		String token = TokenManager.getToken(); // get Token

		ModuleGetByIdResponse response = given().spec(requestSpecification()).header("Authorization", token).when()
				.get(getGlobalValue("resourceModule")+ moduleId + "").then().extract().as(ModuleGetByIdResponse.class);

		Assert.assertEquals(response.getData().getModule().getName(), TestCredentials.Module_Name);
		Assert.assertEquals(response.getData().getModule().getPath(), TestCredentials.Module_Path);
		Assert.assertEquals(response.getData().getModule().getIcon(), TestCredentials.Module_Icon);

	}

	// Delete User - Super Admin and Other
	@Test(priority = 4)
	public void TC04_DeleteModuleByID() throws Exception {
		test = BaseTest.extent.createTest("Delete Module data by ID");
		String token = TokenManager.getToken(); // get Token

		ModuleDeleteResponse regularUserResponse = given().spec(requestSpecification()).header("Authorization", token)
				.when().delete(getGlobalValue("resourceModule") + moduleId + "").then().extract()
				.as(ModuleDeleteResponse.class);

		Assert.assertEquals(regularUserResponse.getStatus(), TestCredentials.Success_Status);
		Assert.assertEquals(regularUserResponse.getMessage(), TestCredentials.Success_Delete);
		Assert.assertEquals(regularUserResponse.getResponseCode(), TestCredentials.StatusCode_OK);

	}

}
