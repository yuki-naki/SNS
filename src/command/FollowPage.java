package command;

import context.ResponseContext;

public class FollowPage extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resc) {
		resc.setTarget("followList");
		return resc;
	}

}
