package context;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext {

	private String target;
	private Object result;
	private HttpServletResponse response;

	public void setTarget(String transferInfo){
		target = "/WEB-INF/jsp/" + transferInfo + ".jsp";
	}

	public String getTarget(){
		return target;
	}

	public void setResult(Object bean){
		result = bean;
	}

	public Object getResult(){
		return result;
	}

	public void setResponse(Object obj) {
		response = (HttpServletResponse) obj;
	}

	public Object getResponse() {
		return response;
	}
}
