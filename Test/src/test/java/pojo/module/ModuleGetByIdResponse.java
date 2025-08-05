package pojo.module;

import pojo.user.UserListDataByID;

public class ModuleGetByIdResponse {
	
	private String status;
    private String message;
    private ModuleListDataById data;
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
	public ModuleListDataById getData() {
		return data;
	}
	public void setData(ModuleListDataById data) {
		this.data = data;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
    
    

}
