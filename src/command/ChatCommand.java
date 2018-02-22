package command;

import context.RequestContext;
import context.ResponseContext;

public class ChatCommand extends AbstractChatCommand {

	public ResponseContext execute(ResponseContext resc) {
		RequestContext requestContext = getRequestContext();

		Object[] chatData = getChatData(requestContext);

		resc.setResult(chatData);
		resc.setTarget("chat");
		return resc;
	}
}
