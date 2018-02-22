package dao;

import java.util.List;

public interface FollowDao {
	public void follow(String followerUserId, String followedUserId);
	public void removeFollow(String followerUserId, String removeTargetUserId);
	public List<String> getFollowIdList(String followerUserId);
	public List<String> getUnFollowIdList(String followerUserId);
	public List<String> getFollowList(String followerUserId);
	public List<String> getUnFollowList(String followerUserId);
}
