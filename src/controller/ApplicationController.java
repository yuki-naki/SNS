package controller;

import context.RequestContext;
import context.ResponseContext;

public interface ApplicationController {

	RequestContext getRequest(Object request);
	ResponseContext handleRequest(RequestContext reqc);
	void handleResponse(RequestContext reqc, ResponseContext resc);
}
