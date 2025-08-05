package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.BaseTest;
import data.TestCredentials;
import io.restassured.RestAssured;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.LoginData;

public class TokenManager extends BaseTest{

	    private static String token = null; // Cache token

	    public static String getToken() {
	        if (token == null) {
	            try {
	                token = new TokenManager().loginAndGetToken(
	                    TestCredentials.VALID_EMAIL, 
	                    TestCredentials.VALID_PASSWORD
	                );
	            } catch (Exception e) {
	                e.printStackTrace();
	                throw new RuntimeException("Failed to generate token", e);
	            }
	        }
	        return token;
	    }
	}

