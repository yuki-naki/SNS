package command;

import context.ResponseContext;

public class ChatRoom extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {
		resc.setTarget("chatRoom");
		return resc;
	}

}
