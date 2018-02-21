package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Group;
import dao.OraGroupDao;

@MultipartConfig(maxFileSize=1000000000)
@WebServlet("/GroupIconUploadServlet")
public class GroupIconUploadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		Part part = request.getPart("iconimg");
		OraGroupDao dao = new OraGroupDao();
		Group g = new Group();

		RequestDispatcher dis = request.getRequestDispatcher("chat");

		if(part.getSize() == 0){

			g.setGroupId(request.getParameter("groupId"));
			g.setGroupName(request.getParameter("groupName"));

			dao.groupUpdate(g);

			dis.forward(request, response);
		}else{

			g.setGroupId(request.getParameter("groupId"));
			g.setGroupName(request.getParameter("groupName"));

			dao.groupUpdate(part.getInputStream(),part.getSize(),g);

			dis.forward(request, response);
		}

	}
}
