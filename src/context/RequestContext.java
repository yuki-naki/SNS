package context;

public interface RequestContext {

	String getCommandPath();
	String[] getParameter(String key);
	Object getRequest();
	void setRequest(Object request);
}
