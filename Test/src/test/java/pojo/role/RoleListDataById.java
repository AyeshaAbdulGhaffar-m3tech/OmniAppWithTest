package pojo.role;

import java.util.List;

import pojo.user.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleListDataById {
	
	private int id;
    private String name;
    private String type;
    private RoleListPermissionDataById permission;
    
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public RoleListPermissionDataById getPermission() {
		return permission;
	}
	public void setPermission(RoleListPermissionDataById permission) {
		this.permission = permission;
	}
    

	
	
	
}
