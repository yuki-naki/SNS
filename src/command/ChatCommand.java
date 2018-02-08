package command;

import java.util.List;

import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.ChatDao;
import dao.OracleConnectionManager;

public class ChatCommand extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {

		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		ChatDao chatDao = factory.getChatDao();
		List<ChatDao> chats = chatDao.getAllChats();

		OracleConnectionManager.getInstance().closeConnection();

		resc.setResult(chats);
		resc.setTarget("chat");
		return resc;
	}
}
