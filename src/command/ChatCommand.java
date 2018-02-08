package command;

import java.util.Map;

import bean.Chat;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.ChatDao;
import dao.OracleConnectionManager;

public class ChatCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();

		String sessionUserId = rc.getParameter("sessionUserId")[0];

		String[] chatIdTab = rc.getParameter("chatId");
		String chatId = null;
		if(chatIdTab != null){
			chatId = chatIdTab[0];
		}

		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		ChatDao chatDao = factory.getChatDao();

		Map<String, Chat> chats = chatDao.getAllChats(sessionUserId);

		OracleConnectionManager.getInstance().closeConnection();

		Object[] array = new Object[2];
		array[0] = chatId;
		array[1] = chats;

		resc.setResult(array);
		resc.setTarget("chat");
		return resc;
	}
}
