package pojo.user;

public class UserGetByIdResponse {
	
	private String status;
    private String message;
    private UserListDataByID data;
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
	public UserListDataByID getData() {
		return data;
	}
	public void setData(UserListDataByID data) {
		this.data = data;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
    
    
    
    
}
