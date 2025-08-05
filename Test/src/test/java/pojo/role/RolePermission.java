package pojo.role;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermission {
	
	private int pageId;
    private List<Integer> actions;
    
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public List<Integer> getActions() {
		return actions;
	}
	public void setActions(List<Integer> actions) {
		this.actions = actions;
	}
    
    


}
