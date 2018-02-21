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

/**
 * Servlet implementation class GroupIconUploadServlet
 */
@MultipartConfig(maxFileSize=1000000000)
@WebServlet("/GroupIconUploadServlet")
public class GroupIconUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupIconUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println("Partファイル生成");
		Part part = request.getPart("iconimg");
		OraGroupDao dao = new OraGroupDao();
		Group g = new Group();

		RequestDispatcher dis = request.getRequestDispatcher("chat");

		System.out.println("part:"+part.getSize());

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
