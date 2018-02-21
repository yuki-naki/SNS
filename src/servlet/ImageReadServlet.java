package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readImage")
public class ImageReadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filepath = request.getParameter("filepath");
		byte[] imageToReturn = convertFileContentToBytes(filepath);

		response.setContentType("image/jpg");

		InputStream in = null;
		OutputStream out = null;

		try{
			response.setContentLength((int) imageToReturn.length);

			in = new ByteArrayInputStream(imageToReturn);
			out = response.getOutputStream();

			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
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

	private static byte[] convertFileContentToBytes(String filePathStr) throws IOException {
		// get path object pointing to file
		Path filePath = Paths.get(filePathStr);
		// get byte array with file contents
		byte[] fileContent = Files.readAllBytes(filePath);
		return fileContent;
	}
}
