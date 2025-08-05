package pojo;

public class LoginData {

		private int id;
		private String name;
	    private String email;
	    private String userName;
	    private String contact;
	    private int roleId;
	    private int isPasswordChanged;
	    private String token;
	    
	    
	    //Getter and Setter
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		public int getIsPasswordChanged() {
			return isPasswordChanged;
		}
		public void setIsPasswordChanged(int isPasswordChanged) {
			this.isPasswordChanged = isPasswordChanged;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
}
