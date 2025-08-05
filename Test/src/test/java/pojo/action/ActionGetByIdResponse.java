package pojo.action;

import pojo.user.UserListDataByID;

public class ActionGetByIdResponse {
	
	private String status;
    private String message;
    private ActionListDataById data;
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
	public ActionListDataById getData() {
		return data;
	}
	public void setData(ActionListDataById data) {
		this.data = data;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
    
    

}
