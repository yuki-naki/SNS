package command;

import context.ResponseContext;

public class MyPage extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {
		resc.setTarget("myPage");
		return resc;
	}

}
