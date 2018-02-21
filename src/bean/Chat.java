package bean;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

public class Chat implements Serializable {

	private String groupId;
	private String groupName;
	private Blob groupIcon;
	private List<User> members;
	private List<User> notMembers;
	private List<Message> messages;

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
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public List<User> getNotMembers() {
		return notMembers;
	}
	public void setNotMembers(List<User> notMembers) {
		this.notMembers = notMembers;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
