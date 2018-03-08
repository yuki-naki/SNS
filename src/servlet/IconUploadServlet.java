package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.User;
import dao.OraUserDao;

/**
 * Servlet implementation class IconTestServlet
 */
@MultipartConfig(maxFileSize=1000000000)
@WebServlet("/uploadIcon")
public class IconUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IconUploadServlet() {
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
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		System.out.println("Partファイル生成");
		Part part = request.getPart("iconimg");

		System.out.println("part:"+part.getSize());

		if(part.getSize() == 0){
			RequestDispatcher dis = request.getRequestDispatcher("myPageSetup");

			dis.forward(request, response);
		}else{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");


			 OraUserDao dao = new OraUserDao();
			 dao.iconUpdate(part.getInputStream(),part.getSize(),user.getUserId());


			 RequestDispatcher dis = request.getRequestDispatcher("myPageSetup");

			 dis.forward(request, response);
		}

	}

}

