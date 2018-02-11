package context;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebRequestContext implements RequestContext {

	private Map parameters;
	private HttpServletRequest request;

	public Object getRequest(){
		return request;
	}

	public void setRequest(Object req){
		request = (HttpServletRequest) req;
		parameters = request.getParameterMap();
	}

	public String getCommandPath(){
		String servletPath = request.getServletPath();
		String commandPath = servletPath.substring(1);
		return commandPath;
	}

	public String[] getParameter(String key){
		return (String[]) parameters.get(key);
	}

	public String getTextarea(){
		return (String) request.getAttribute("textarea");
	}

	public Object getSessionObject(String object){
		return request.getSession().getAttribute(object);
	}

	public String convertObjectToString(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String string = null;
		try { string = objectMapper.writeValueAsString(object); }
		catch (IOException e) { e.printStackTrace(); }
		return string;
	}
}
