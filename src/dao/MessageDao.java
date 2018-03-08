package dao;

import java.util.List;

import bean.Message;

public interface MessageDao {
	public void addMessage(Message message);
	public List<Message> getMessageList(String groupId);
}
