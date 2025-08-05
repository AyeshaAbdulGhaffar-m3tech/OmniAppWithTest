package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import utils.TestDataUtil;

import static io.restassured.RestAssured.given;

public class BaseTest {
	
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));
		
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		
			return new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					 .addFilter(RequestLoggingFilter.logRequestTo(log))
					 .addFilter(ResponseLoggingFilter.logResponseTo(log))
					 .setContentType(ContentType.JSON).build()
					 ;
			// return reqSpec;
		
		//return reqSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/base/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public String getJsonPath(Response response, String key) {
//		String res= response.asString();
//	    JsonPath js = new JsonPath(res);
//	   return  js.get(key).toString();
	   
	   String res = response.asString();
	    JsonPath js = new JsonPath(res);
	    Object value = js.get(key);
	    
	    if (value == null) {
	        throw new RuntimeException("Key not found in JSON response: " + key + "\nResponse: " + res);
	    }

	    return value.toString();
	    
	}
	
	//------------------------- Login and Get Token ----------------------------------------//
	
	public String loginAndGetToken(String email, String password) throws Exception {
		
		 LoginRequest request = TestDataUtil.getLoginRequest(email, password);
		 
	    Response loginResponse = given()
	            .spec(requestSpecification())
	            .body(request)
	            .when()
	            .post("/auth/login");
	    String token = getJsonPath(loginResponse, "data.token");
	    if (token == null) {
	        throw new RuntimeException("Token not found in login response: " + loginResponse.asPrettyString());
	    }
	    return "Bearer " + token;
	    //return getJsonPath(loginResponse, "data.token");
	}
	//------------------------- Login and Get Token ----------------------------------------//
	
	//------------------------ Extent Reporting ------------------------------------------//
	public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
    
  //------------------------ Extent Reporting ------------------------------------------//

}
