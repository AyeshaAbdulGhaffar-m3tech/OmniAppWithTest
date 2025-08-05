package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.pages.PageCreateRequest;
import pojo.pages.PageCreateResponse;
import pojo.pages.PageData;
import pojo.pages.PageDeleteResponse;
import pojo.pages.PageGetByIdResponse;
import utils.TokenManager;

public class PagesAPITest extends BaseTest {

	int pageId = -1;

	@DataProvider(name = "createPageData")
	public Object[][] createPageData() {
		return new Object[][] {
				// Positive
				{ TestCredentials.Page_Name, TestCredentials.Page_Path, TestCredentials.Page_Icon, 26,
						TestCredentials.Success_Status, TestCredentials.StatusCode_OK,
						TestCredentials.Success_Message },
				// Negative - Duplicate
				{ TestCredentials.Page_Name, TestCredentials.Page_Path, TestCredentials.Page_Icon, 26, 
						TestCredentials.Success_Error, TestCredentials.StatusCode_BadRequest,
						TestCredentials.Page_Duplicate_Message },
				
				// Negative - Invalid Parent ID [NOt Exsist]
				{ TestCredentials.Page_Name, TestCredentials.Page_Path, TestCredentials.Page_Icon, 987,
						TestCredentials.Success_Error, TestCredentials.StatusCode_BadRequest,
						TestCredentials.Page_Invalid_ParentId_Message },

		};
	}

	// All Test cases related to create user
	@Test(dataProvider = "createPageData", priority = 1)
	public void TC01_CreatePage(String name, String path, String icon, int parentId,  String status, int expectedStatusCode,
			String expectedMessage) throws Exception {

		test = BaseTest.extent.createTest("Create Page Test Cases");

		String token = TokenManager.getToken(); // get Token

		// Create Object and then set values
		PageCreateRequest PageCreateRequest = new PageCreateRequest();

		PageCreateRequest.setName(name);
		;
		PageCreateRequest.setPath(path);
		;
		PageCreateRequest.setIcon(icon);
		;
		PageCreateRequest.setParentId(parentId);
		;

		PageCreateResponse response = given().spec(requestSpecification()).header("Authorization", token)
				.body(PageCreateRequest).when().post(getGlobalValue("resourcePage")).then().extract().as(PageCreateResponse.class);

		Assert.assertEquals(response.getStatus(), status);
		Assert.assertEquals(response.getMessage(), expectedMessage);
		Assert.assertEquals(response.getResponseCode(), expectedStatusCode);
	}

	// Get User and get Id of created user
	@Test(priority = 2)
	public void TC02_GetPage() throws Exception {

		test = BaseTest.extent.createTest("Get Data of all Pages");
		String token = TokenManager.getToken(); // get Token

		Response response = given().spec(requestSpecification()).header("Authorization", token).when().get(getGlobalValue("resourcePage"))
				.then().extract().response();

		// Response in variable
		String message = getJsonPath(response, "message");
		Assert.assertEquals(message, "Successfully Get");

		String status = getJsonPath(response, "status");
		Assert.assertEquals(status, TestCredentials.Success_Status);

		int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
		Assert.assertEquals(responseCode, TestCredentials.StatusCode_OK);

		// Get the list of users as POJOs
		List<PageData> pages = response.jsonPath().getList("data.page", PageData.class);

		// Extract ID of user with specific email

		for (PageData page : pages) {

			// System.out.println("User: " + module.get() + " | RoleID: " +
			// module.getRoleId());

			// System.out.println(module.getEmail());
			if (page.getName().equalsIgnoreCase(TestCredentials.Page_Name)
					&& page.getPath().equalsIgnoreCase(TestCredentials.Page_Path)) {
				pageId = page.getId();
				break;
			}

		}

	}

	// Get User and get Id of created user
	@Test(priority = 3)
	public void TC03_GetPageByID() throws Exception {
		test = BaseTest.extent.createTest("GET Page data by ID");
		String token = TokenManager.getToken(); // get Token

		PageGetByIdResponse response = given().spec(requestSpecification()).header("Authorization", token).when()
				.get(getGlobalValue("resourcePage")+ pageId + "").then().extract().as(PageGetByIdResponse.class);

		Assert.assertEquals(response.getData().getPage().getName(), TestCredentials.Page_Name);
		Assert.assertEquals(response.getData().getPage().getPath(), TestCredentials.Page_Path);
		Assert.assertEquals(response.getData().getPage().getIcon(), TestCredentials.Page_Icon);
		//Assert.assertEquals(response.getData().getPage().getIcon(), TestCredentials.Page_Icon);

	}

	// Delete User - Super Admin and Other
	@Test(priority = 4)
	public void TC04_DeletePageByID() throws Exception {
		test = BaseTest.extent.createTest("Delete Page data by ID");
		String token = TokenManager.getToken(); // get Token

		PageDeleteResponse regularUserResponse = given().spec(requestSpecification()).header("Authorization", token)
				.when().delete(getGlobalValue("resourcePage") + pageId + "").then().extract()
				.as(PageDeleteResponse.class);

		Assert.assertEquals(regularUserResponse.getStatus(), TestCredentials.Success_Status);
		Assert.assertEquals(regularUserResponse.getMessage(), TestCredentials.Success_Delete);
		Assert.assertEquals(regularUserResponse.getResponseCode(), TestCredentials.StatusCode_OK);

	}

}
