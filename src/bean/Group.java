package bean;

import java.io.Serializable;
import java.sql.Blob;

public class Group implements Serializable{
	private String groupId;
	private String groupName;
	private Blob groupIcon;

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Blob getGroupIcon() {
		return groupIcon;
	}
	public void setGroupIcon(Blob groupIcon) {
		this.groupIcon = groupIcon;
	}

}
