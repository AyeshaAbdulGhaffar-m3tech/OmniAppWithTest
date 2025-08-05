package pojo;

import com.fasterxml.jackson.databind.JsonNode;

public class LoginResponse {

	 private String status;
	 private String message;
	 private JsonNode data;
	 private int responseCode;
	 
	 
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 public String getMessage() {
		 return message;
	 }
	 public void setMessage(String message) {
		 this.message = message;
	 }
	 public JsonNode getData() {
		 return data;
	 }
	 public void setData(JsonNode data) {
		 this.data = data;
	 }
	 public int getResponseCode() {
		 return responseCode;
	 }
	 public void setResponseCode(int responseCode) {
		 this.responseCode = responseCode;
	 }
	 
	 
	 
	 
	 
}
