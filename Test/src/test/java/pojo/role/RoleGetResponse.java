package pojo.role;

import java.util.List;

public class RoleGetResponse {

	 private String status;
	    private String message;
	    private List<RoleData> data;
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
		public List<RoleData> getData() {
			return data;
		}
		public void setData(List<RoleData> data) {
			this.data = data;
		}
		public int getResponseCode() {
			return responseCode;
		}
		public void setResponseCode(int responseCode) {
			this.responseCode = responseCode;
		}
	    
	    

	
	
}
