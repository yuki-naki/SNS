package dao;

import java.util.Map;

public interface ChatDao {
	Map getAllChats(String sessionUserId);
}
