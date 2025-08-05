package pojo.role;

import java.util.List;

public class RoleCreateRequest {
	private String name;
    private String description;
    private String type;
    private List<RolePermission> permissions;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<RolePermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}

    
    
	
	

}
