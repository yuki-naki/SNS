package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.OraUserDao;

@MultipartConfig(maxFileSize=1000000000)
@WebServlet("/loadIcon")
public class ImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ImageServletStart");
		OraUserDao dao = new OraUserDao();
		User user = dao.getUserByUserIcon(request.getParameter("userId"));

		Blob imageToReturn = user.getIcon();
		// query your DB (or other data source) to get a blob representation of your image
		// Set content type
		response.setContentType("image/png");

		InputStream in = null;
		OutputStream out = null;

		try{
			response.setContentLength((int) imageToReturn.length());

			in = imageToReturn.getBinaryStream();
			out = response.getOutputStream();

			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
		long end = System.currentTimeMillis();
		} catch (SQLException e) {
			// do something useful when reading image from DB fails
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
			}
		}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
