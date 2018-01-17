package context;

public interface ResponseContext {

	Object getResult();
	String getTarget();
	void setResult(Object bean);
	void setTarget(String transferInfo);
	void setResponse(Object obj);
	Object getResponse();
}
