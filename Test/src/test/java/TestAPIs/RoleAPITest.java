package TestAPIs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.response.Response;
import pojo.role.RoleCreateRequest;
import pojo.role.RoleCreateResponse;
import pojo.role.RoleData;
import pojo.role.RoleDeleteResponse;
import pojo.role.RoleGetByIdResponse;
import pojo.role.RolePermission;
import utils.TestDataUtil;
import utils.TokenManager;

public class RoleAPITest extends BaseTest {

	int roleId = -1;

	@DataProvider(name = "createRoleData")
	public Object[][] createRoleData() {
		return new Object[][] {
				// Positive
				{"Valid Role Creation", TestCredentials.Role_Name, TestCredentials.Role_Decription, TestCredentials.Role_Type_User,
						TestCredentials.Success_Status, TestCredentials.StatusCode_OK,
						TestCredentials.Role_Success_Message },
				// Negative - Invalid Type
				{ "Duplicate Role Name", TestCredentials.Role_Name, TestCredentials.Role_Decription, TestCredentials.Role_Type_User,
						TestCredentials.Success_Error, TestCredentials.StatusCode_Forbidden,
						TestCredentials.Role_Duplicate_Message },
				
				// Negative - Invalid page Id
				{ "Invalid Page ID", TestCredentials.Role_Name_New, TestCredentials.Role_Decription, TestCredentials.Role_Type_User,
						TestCredentials.Success_Error, TestCredentials.StatusCode_Forbidden,
						TestCredentials.Role_Invalid_Page_Message },
				
				// Negative - Invalid Action ID
				{ "Invalid Action Id", TestCredentials.Role_Name_New, TestCredentials.Role_Decription, TestCredentials.Role_Type_User,
						TestCredentials.Success_Error, TestCredentials.StatusCode_Forbidden,
						TestCredentials.Role_Invalid_Action_Message },
				

				// Negative - Invalid Type
				{ "Invalid Type", TestCredentials.Role_Name_New, TestCredentials.Role_Decription, TestCredentials.Role_Type_User_Invalid,
						TestCredentials.Success_Error, TestCredentials.StatusCode_BadRequest,
						TestCredentials.Role_Invalid_Body_Message },
				
			

		};
	}

	// All Test cases related to create user
	@Test(dataProvider = "createRoleData", priority = 1)
	public void TC01_CreateRole(String scenario, String name, String description, String type,  String expectedstatus, int expectedStatusCode,
			String expectedMessage) throws Exception {

		test = BaseTest.extent.createTest("Create Role Test Case: " + scenario);

        String token = TokenManager.getToken();

        List<Integer> pageIds = TestDataUtil.getRandomValidPageIds(token, 2); // get 2 unique page IDs
        List<Integer> actionIds = TestDataUtil.getRandomValidActionIds(token);

        List<RolePermission> permissions = new ArrayList<>();

        if (scenario.equals("Invalid Page ID")) {
            // Use invalid page ID
            RolePermission perm = new RolePermission();
            perm.setPageId(-99); // assumed invalid
            perm.setActions(actionIds);
            permissions.add(perm);
        } else if (scenario.equals("Invalid Action Id")) {
            RolePermission perm = new RolePermission();
            perm.setPageId(pageIds.get(0));
            perm.setActions(Arrays.asList(-99, -88)); // invalid action IDs
            permissions.add(perm);
        } else {
            // Success / Invalid Type etc. — use valid data
            for (int pageId : pageIds) {
                RolePermission perm = new RolePermission();
                perm.setPageId(pageId);
                
                System.out.println(pageId);
                perm.setActions(actionIds.subList(0, Math.min(3, actionIds.size())));
                
                System.out.println(actionIds);
                permissions.add(perm);
            }
        }

        RoleCreateRequest request = new RoleCreateRequest();
        request.setName(name);
        request.setDescription(description);
        request.setType(type);
        request.setPermissions(permissions);

        RoleCreateResponse response = given()
                .spec(requestSpecification())
                .header("Authorization", token)
                .body(request)
                .when()
                .post(getGlobalValue("resourceRole"))
                .then()
                .extract()
                .as(RoleCreateResponse.class);

        Assert.assertEquals(response.getStatus(), expectedstatus);
        Assert.assertEquals(response.getMessage(), expectedMessage);
        Assert.assertEquals(response.getResponseCode(), expectedStatusCode);
	}

	// Get User and get Id of created user
	@Test(priority = 2)
	public void TC02_GetRole() throws Exception {

		test = BaseTest.extent.createTest("Get Data of all Role");
		String token = TokenManager.getToken(); // get Token

		Response response = given().spec(requestSpecification()).header("Authorization", token).when().get(getGlobalValue("resourceRole"))
				.then().extract().response();

		// Response in variable
		String message = getJsonPath(response, "message");
		Assert.assertEquals(message, TestCredentials.Role_Get_Success_Message);

		String status = getJsonPath(response, "status");
		Assert.assertEquals(status, TestCredentials.Success_Status);

		int responseCode = Integer.parseInt(getJsonPath(response, "responseCode"));
		Assert.assertEquals(responseCode, TestCredentials.StatusCode_OK);

		 // Validate top-level fields
	    String actualName = response.path("data[0].name");
	    String actualType = response.path("data[0].type");
	    String actualDescription = response.path("data[0].description");

	    Assert.assertEquals(actualName, TestCredentials.Role_Name, "Role name mismatch");
	    Assert.assertEquals(actualType, TestCredentials.Role_Type_User, "Role type mismatch");
	    Assert.assertEquals(actualDescription,TestCredentials.Role_Decription, "Description mismatch");

	  

	    		// Convert the "data" array into list of RoleData
	    		List<RoleData> roles = response.jsonPath().getList("data", RoleData.class);

	    		
	    		for (RoleData role : roles) {
	    		    if (role.getName().equalsIgnoreCase(TestCredentials.Role_Name)) {
	    		        roleId = role.getId();
	    		        break;
	    		    }
	    		}

	    		System.out.println("Extracted Role ID: " + roleId);


	}

	// Get User and get Id of created user
	@Test(priority = 3)
	public void TC03_GetROleByID() throws Exception {
		test = BaseTest.extent.createTest("GET Role data by ID");
		String token = TokenManager.getToken(); // get Token

		RoleGetByIdResponse response = given().spec(requestSpecification()).header("Authorization", token).when()
				.get(getGlobalValue("resourceRole")+ roleId + "").then().extract().as(RoleGetByIdResponse.class);

		Assert.assertEquals(response.getMessage(), TestCredentials.Role_Get_Success_ByID_Message);
		Assert.assertEquals(response.getData().getName(), TestCredentials.Role_Name);
		Assert.assertEquals(response.getData().getType(), TestCredentials.Role_Type_User);
		Assert.assertEquals(response.getData().getPermission().getDescription(), TestCredentials.Role_Decription);
		//Assert.assertEquals(response.getData().getPage().getnIcon(), TestCredentials.Page_Icon);

	}
	
	
	// Delete User - Super Admin and Other
	@Test(priority = 4)
	public void TC04_DeleteRoleByID() throws Exception {
		test = BaseTest.extent.createTest("Delete Role data by ID");
		String token = TokenManager.getToken(); // get Token

		RoleDeleteResponse regularUserResponse = given().spec(requestSpecification()).header("Authorization", token)
				.when().delete(getGlobalValue("resourceRole") + roleId + "").then().extract()
				.as(RoleDeleteResponse.class);

		
		Assert.assertEquals(regularUserResponse.getStatus(), TestCredentials.Success_Status);
		Assert.assertEquals(regularUserResponse.getMessage(), TestCredentials.Role_Delete_Successfully_Message);
		Assert.assertEquals(regularUserResponse.getResponseCode(), TestCredentials.StatusCode_OK);

	}



}
