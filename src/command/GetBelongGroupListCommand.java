package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupDao;

public class GetBelongGroupListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupDao groupDao = factory.getGroupDao();

		//SessionからUser情報取得
		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		responseContext.setTarget("deleteGroup(test)");
		responseContext.setResult((Object)groupDao.getBelongGroupList(userId));

		return responseContext;
	}
}
