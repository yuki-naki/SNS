package bean;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

public class Chat implements Serializable {

	private String groupId;
	private String groupName;
	private Blob groupIcon;
	private List<User> users;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
