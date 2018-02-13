package context;

public interface RequestContext {

	String getCommandPath();
	String[] getParameter(String key);
	Object getRequest();
	void setRequest(Object request);
	String getTextarea();
	Object getSessionObject(String object);
	String convertObjectToString(Object object);
}
