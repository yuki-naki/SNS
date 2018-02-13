package command;

import context.ResponseContext;

public class FollowList extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {
		resc.setTarget("followList");
		return resc;
	}

}