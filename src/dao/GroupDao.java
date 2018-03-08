package dao;

import java.io.FileInputStream;
import java.util.ArrayList;

import bean.Group;

public interface GroupDao {
	public Group getGroup(String groupId);
	public ArrayList<Group> getGroupList(ArrayList<String> groupIdList);
	public String createGroup(Group group, FileInputStream inputStream, int imageSize);
	public void deleteGroup(String deleteTargetGroupId);
	public void updateGroupDate(String groupId);
}
