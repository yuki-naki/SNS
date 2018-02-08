package command;

import context.ResponseContext;

public class TopPage extends AbstractCommand {

	public ResponseContext execute(ResponseContext resc) {
		resc.setTarget("topPage");
		return resc;
	}
}
