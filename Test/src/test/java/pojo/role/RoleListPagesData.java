package pojo.role;

import java.util.List;

public class RoleListPagesData {
	
	private int id;
    private String name;
    private Integer parentId;
    private String path;
    private String icon;
    private List<RoleListActionsData> actions;
    
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<RoleListActionsData> getActions() {
		return actions;
	}
	public void setActions(List<RoleListActionsData> actions) {
		this.actions = actions;
	}
    
    
    

}
