package pojo.pages;

import pojo.user.UserListDataByID;

public class PageGetByIdResponse {
	
	private String status;
    private String message;
    private PageListDataById data;
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
	public PageListDataById getData() {
		return data;
	}
	public void setData(PageListDataById data) {
		this.data = data;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
    
    

}
