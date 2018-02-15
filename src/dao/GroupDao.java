package dao;

import java.util.ArrayList;

import bean.Group;

public interface GroupDao {
	public Group getGroup(String groupId);
	public String createGroup(Group group);
	public ArrayList<String> getBelongGroups(String userId);
}
