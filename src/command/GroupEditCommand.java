package command;

import java.util.ArrayList;

import bean.Group;
import context.RequestContext;
import context.ResponseContext;
import dao.OraGroupDao;

public class GroupEditCommand extends AbstractCommand{

	public ResponseContext execute(ResponseContext resc) {

		RequestContext rc = getRequestContext();
		ArrayList list = new ArrayList();
		Group g = null;
		OraGroupDao dao = new OraGroupDao();

		String groupId = rc.getParameter("groupId")[0];
		g = dao.getGroup(groupId);

		list.add(g);
		resc.setResult(list);

		resc.setTarget("groupEdit");
		return resc;
	}

}
