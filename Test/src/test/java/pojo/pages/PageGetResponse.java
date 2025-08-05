package pojo.pages;

public class PageGetResponse {

	private String status;
	private String message;
	private String responseCode;
	private PageListData Data;
	
	
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
	public PageListData getData() {
		return Data;
	}
	public void setData(PageListData data) {
		Data = data;
	}
	
	
}
