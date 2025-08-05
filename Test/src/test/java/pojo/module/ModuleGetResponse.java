package pojo.module;

public class ModuleGetResponse {

	private String status;
	private String message;
	private String responseCode;
	private ModuleListData Data;
	
	
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
	public ModuleListData getData() {
		return Data;
	}
	public void setData(ModuleListData data) {
		Data = data;
	}
	
	
}
