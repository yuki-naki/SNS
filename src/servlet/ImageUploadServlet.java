package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@MultipartConfig(maxFileSize=1000000000)
@WebServlet("/notify")
public class ImageUploadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String textarea = request.getParameter("textarea2");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if(isMultipart){
		    for(Part part : request.getParts()) {
		    	String fieldname = part.getName();
		        if(fieldname.startsWith("file")) {
		            InputStream fileContent = part.getInputStream();
		            Calendar calendar = Calendar.getInstance(new Locale("ja", "JAPAN"));
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		            String updatedFileName =  sdf.format(calendar.getTime())+".jpg";
		            try {
		            	String filePath = "C:/workspace/SNS/WebContent/uploaded/"+updatedFileName;
		                OutputStream os = new FileOutputStream(filePath);

		                byte[] buffer = new byte[1024];
		                int bytesRead;
		                //read from inputstream to buffer
		                while((bytesRead = fileContent.read(buffer)) !=-1){
		                    os.write(buffer, 0, bytesRead);
		                }
		                fileContent.close();
		                //flush OutputStream to write any buffered data to file
		                os.flush();
		                os.close();

		                //filePath = "uploaded/"+updatedFileName;
		                filePath = "readImage?filepath=C:/workspace/SNS/WebContent/uploaded/"+updatedFileName;
		                String[] splitFieldname = fieldname.split("-");
		                String id = splitFieldname[1];
		                textarea = textarea.replaceFirst("<img id=\""+id+"\" src=\"\"", "<img id=\""+id+"\" src=\""+filePath+"\"");

		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}

	    request.setAttribute("textarea", textarea);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/notifyCommand");

        dispatcher.forward(request,response);
	}
}
