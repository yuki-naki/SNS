package servlet;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

@MultipartConfig(maxFileSize=2000000000)
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String textarea = request.getParameter("textarea2");
		System.out.println(textarea);

/*
		int startIndex = textarea.indexOf("<img");
	    int endIndex = textarea.indexOf("/>");
	    String img = textarea.substring(startIndex, endIndex);

	    int startIndexBase64 = img.indexOf("base64");
	    String base64 = img.substring(startIndexBase64+7);
	    int endIndexBase64 = base64.indexOf("\"");
	    base64 = base64.substring(0, endIndexBase64);

	    byte[] decodedImg = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
	    InputStream in = new ByteArrayInputStream(decodedImg);
	    //String updatedFileName = UUID.randomUUID().toString() + ".jpg";
*/
	    // BufferedImage ImageFromConvert = ImageIO.read( in );

	    //InputStream input = getServletContext().getResourceAsStream("/");
	    //String path = "D:/Lessons/JE22/workspace2/SNS/WebContent/WEB-INF/uploaded/"+updatedFileName;
	  //  File profilePicUrl = new File(path);
	  //  ImageIO.write(ImageFromConvert, "jpg", profilePicUrl);
	  //  in.close();

	    //part.write(path + "/" + updatedFileName);
/*
	    Part part = request.getPart("file");
	    InputStream fileContent = part.getInputStream();

	    */

	    for (Part part : request.getParts()) {
	        String filename = getFilename(part);
	        System.out.println(filename);
	        if (filename == null) {
	            // Traiter les champs classiques ici (input type="text|radio|checkbox|etc", select, etc).
	            String fieldname = part.getName();
	            String fieldvalue = getValue(part);
	            // ... (traitement à faire)
	        } else if (!filename.isEmpty()) {
	            // Traiter les champs de type fichier (input type="file").
	            String fieldname = part.getName();
	            filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	            System.out.println(fieldname+" "+filename);
	            InputStream fileContent = part.getInputStream();
	            Calendar calendar = Calendar.getInstance(new Locale("ja", "JAPAN"));
	    	    int year = calendar.get(Calendar.YEAR);
	    	    int month = calendar.get(Calendar.MONTH)+1;
	    	    int day = calendar.get(Calendar.DAY_OF_MONTH);
	    	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    	    int min = calendar.get(Calendar.MINUTE);
	    	    int sec = calendar.get(Calendar.SECOND);
	    	    int milli = calendar.get(Calendar.MILLISECOND);
	    	    String updatedFileName = ""+year+month+day+hour+min+sec+milli+".jpg";
	            try {

	                OutputStream os = new FileOutputStream("D:/Lessons/JE22/workspace2/SNS/WebContent/WEB-INF/uploaded/"+updatedFileName);

	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                //read from is to buffer
	                while((bytesRead = fileContent.read(buffer)) !=-1){
	                    os.write(buffer, 0, bytesRead);
	                }
	                fileContent.close();
	                //flush OutputStream to write any buffered data to file
	                os.flush();
	                os.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

        RequestDispatcher r= request.getRequestDispatcher("/topPage.jsp");

		r.forward(request,response);
	}

	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

	private static String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	}
}
