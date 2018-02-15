package dao;

import java.util.ArrayList;
import java.util.List;

public interface GroupMemberDao {
	public List<String> getGroupMember(String groupId);
	public List<String> getNotGroupMember(String groupId);
	public void addGroupMember(String groupId, ArrayList<String> members);
	public void removeGroupMember(String groupId, ArrayList<String> members);
}
