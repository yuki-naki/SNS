package command;

import context.ResponseContext;

public class MyPageSetupCommand extends AbstractCommand{

	@Override
	public ResponseContext execute(ResponseContext resc) {

		Session session = resc.getR


		resc.setTarget("myPage");
		return resc;
	}

}
