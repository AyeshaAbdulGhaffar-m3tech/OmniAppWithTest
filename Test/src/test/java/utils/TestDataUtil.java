package utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import base.BaseTest;
import io.restassured.response.Response;
import pojo.LoginRequest;
import pojo.ResetPasswordRequest;


public class TestDataUtil extends BaseTest {
	
	private static final Random random = new Random();
	static BaseTest baseTest = new BaseTest();
	
	public static LoginRequest getLoginRequest(String email, String password) {
        return new LoginRequest(email, password);
    }

	public static ResetPasswordRequest getResetPasswordRequest(String currentPassword, String NewPassword) {
        return new ResetPasswordRequest(currentPassword, NewPassword);
    }
	

    
    public static List<Integer> getRandomValidPageIds(String token, int count) throws IOException {
        Response response = given().spec(baseTest.requestSpecification()).header("Authorization", token)
                .when().get(getGlobalValue("resourcePage"))
                .then().extract().response();

        List<Integer> pageIds = response.jsonPath().getList("data.page.id");
        if (pageIds == null || pageIds.isEmpty()) {
            throw new RuntimeException("No valid page IDs found");
        }

        Collections.shuffle(pageIds);
        return pageIds.subList(0, Math.min(count, pageIds.size()));
    }

    public static List<Integer> getRandomValidActionIds(String token) throws IOException {
        Response response = given().spec(baseTest.requestSpecification()).header("Authorization", token)
                .when().get(getGlobalValue("resourceAction"))
                .then().extract().response();

        List<Integer> actionIds = response.jsonPath().getList("data.action.id");
        if (actionIds == null || actionIds.isEmpty()) {
            throw new RuntimeException("No valid action IDs found");
        }

        Collections.shuffle(actionIds);
        return actionIds.subList(0, Math.min(4, actionIds.size()));
    }

}
