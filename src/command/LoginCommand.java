package command;

import context.ResponseContext;

public class LoginCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext responseContext){

		responseContext.setTarget("topPage");
		return responseContext;
	}
}
