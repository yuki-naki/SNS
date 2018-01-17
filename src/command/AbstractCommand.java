package command;

import context.RequestContext;
import context.ResponseContext;

public abstract class AbstractCommand {

	private RequestContext rc;

	public void init(RequestContext reqContext){
		rc = reqContext;
	}

	protected RequestContext getRequestContext() {
		return rc;
	}

	public abstract ResponseContext execute(ResponseContext resc);
}
