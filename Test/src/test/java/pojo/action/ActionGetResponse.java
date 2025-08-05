package pojo.action;

public class ActionGetResponse {

	private String status;
	private String message;
	private String responseCode;
	private ActionListData Data;
	
	
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
	public ActionListData getData() {
		return Data;
	}
	public void setData(ActionListData data) {
		Data = data;
	}
	
	
}
