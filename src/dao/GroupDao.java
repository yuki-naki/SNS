package dao;

import java.util.ArrayList;

import bean.Group;

public interface GroupDao {
	public Group getGroup(String groupId);
	public ArrayList<Group> getGroupList(ArrayList<String> groupIdList);
	public String createGroup(Group group);
	public void deleteGroup(String deleteTargetGroupId);
}
