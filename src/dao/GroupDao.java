package dao;

import java.util.ArrayList;

import bean.Group;

public interface GroupDao {
	public Group getGroup(String groupId);
	public String createGroup(Group group);
	public void deleteGroup(String deleteTargetGroupId);
	public ArrayList<Group> getBelongGroupList(String userId);
	public ArrayList<String> getBelongGroupIdList(String userId);
}
