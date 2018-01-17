package context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
}
