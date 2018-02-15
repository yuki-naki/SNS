package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.GroupDao;

public class DeleteGroupCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){

		RequestContext rc = getRequestContext();
		HttpServletRequest request = (HttpServletRequest)rc.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();

		//フォロー解除対象のユーザーIDを取得
		String deleteTargetGroupId = rc.getParameter("deleteTargetGroupId")[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		GroupDao groupDao = factory.getGroupDao();

		groupDao.deleteGroup(deleteTargetGroupId);
		responseContext.setTarget("deleteGroup(test)");
		responseContext.setResult((Object)groupDao.getBelongGroupList(userId));
		return responseContext;
	}
}
