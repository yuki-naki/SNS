package command;

import context.ResponseContext;

public class MyPageSetupCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resc) {



		resc.setTarget("myPage");
		return resc;
	}

}
