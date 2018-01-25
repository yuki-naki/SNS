package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import context.RequestContext;
import context.ResponseContext;
import dao.AbstractDaoFactory;
import dao.LoginDao;

public class LoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext responseContext){
		RequestContext requestContext = getRequestContext();

		//パラメータ取得
		String loginId = (requestContext.getParameter("loginId"))[0];
		String password = (requestContext.getParameter("password"))[0];

		//dao取得
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
		LoginDao loginDao = factory.getLoginDao();

		//入力がnullでなければログインチェック
		if (loginId != null && password != null)
		{
			//成功ならtrue失敗ならfalse
			String userId = loginDao.login(loginId, password);

			if(userId != null){
				HttpSession session = ((HttpServletRequest)requestContext.getRequest()).getSession();
				session.setAttribute("loginUserId", userId);

				responseContext.setTarget("top");
				return responseContext;
			}
		}

		responseContext.setTarget("login");
		return responseContext;
	}
}
