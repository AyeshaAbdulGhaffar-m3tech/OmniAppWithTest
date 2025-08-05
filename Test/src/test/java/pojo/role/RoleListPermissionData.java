package pojo.role;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleListPermissionData {
	
	private int id;
    private String name;
    private Integer parentId;
    private String path;
    private List<RoleListPagesData> pages;
    
    
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
	public List<RoleListPagesData> getPages() {
		return pages;
	}
	public void setPages(List<RoleListPagesData> pages) {
		this.pages = pages;
	}

    // Getters and Setters
    
    
    
    

}
