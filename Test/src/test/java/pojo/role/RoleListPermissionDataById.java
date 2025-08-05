package pojo.role;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleListPermissionDataById {
	
	private int id;
    private String name;
    private String type;
    private String description;
    private List<RoleListPermissionData> permissionList;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RoleListPermissionData> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<RoleListPermissionData> permissionList) {
		this.permissionList = permissionList;
	}
    
    
	
    
    
    

}
