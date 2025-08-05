package pojo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pojo.ErrorData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateResponse {
	
	private String status;
	private String message;
	private int responseCode;
	 private List<ErrorData> data;
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
	 public int getResponseCode() {
		 return responseCode;
	 }
	 public void setResponseCode(int responseCode) {
		 this.responseCode = responseCode;
	 }
	 public List<ErrorData> getData() {
		 return data;
	 }
	 public void setData(List<ErrorData> data) {
		 this.data = data;
	 }
	 
	
	
	

}
