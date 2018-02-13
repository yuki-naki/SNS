package dao;

import java.util.List;

public interface FollowDao {
	public void follow(String followerUserId, String followedUserId);
	public void removeFollow(String followerUserId, String removeTargetUserId);
	public List getFollowList(String followerUserId);
	public List getUnFollowList(String followerUserId);
}
