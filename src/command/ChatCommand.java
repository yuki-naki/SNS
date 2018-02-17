package command;

import java.util.Map;

import bean.Chat;
import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.ChatDao;

public class ChatCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();

		User sessionUser = (User)rc.getSessionObject("user");
		String sessionUserId = sessionUser.getUserId();
		String userString = rc.convertObjectToString(sessionUser);

		String[] chatIdTab = rc.getParameter("chatId");
		String chatId = null;
		if(chatIdTab != null){
			chatId = chatIdTab[0];
		}

		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		ChatDao chatDao = factory.getChatDao();

		Map<String, Chat> chats = chatDao.getAllChats(sessionUserId);

		//OracleConnectionManager.getInstance().closeConnection();

		Object[] array = new Object[3];
		array[0] = chatId;
		array[1] = chats;
		array[2] = userString;

		resc.setResult(array);
		resc.setTarget("chat");
		return resc;
	}
}
