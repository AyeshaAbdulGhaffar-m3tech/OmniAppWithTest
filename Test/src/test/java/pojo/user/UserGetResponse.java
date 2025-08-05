package pojo.user;

import java.util.List;

public class UserGetResponse {
	
	private String status;
	private String message;
	private String responseCode;
	private UserListData Data;
	
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
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public UserListData getData() {
		return Data;
	}
	public void setData(UserListData data) {
		Data = data;
	}
	
	
}
