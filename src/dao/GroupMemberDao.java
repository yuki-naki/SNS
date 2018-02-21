package dao;

import java.util.ArrayList;
import java.util.List;

public interface GroupMemberDao {
	public List<String> getGroupMemberIdList(String groupId);
	public List<String> getNotGroupMemberIdList(String groupId);
	public List<String> getBelongGroupIdList(String userId);
	public void addGroupMember(String groupId, ArrayList<String> members);
	public void removeGroupMember(String groupId, ArrayList<String> members);
}
