package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.AbstractCommand;
import command.CommandFactory;
import context.RequestContext;
import context.ResponseContext;
import context.WebRequestContext;
import context.WebResponseContext;

public class WebApplicationController implements ApplicationController {

	public RequestContext getRequest(Object request){
		RequestContext reqc = new WebRequestContext();
		reqc.setRequest(request);
		return reqc;
	}

	public ResponseContext handleRequest(RequestContext reqc){
		AbstractCommand command = CommandFactory.getCommand(reqc);
		command.init(reqc);
		ResponseContext resc = command.execute(new WebResponseContext());
		return resc;
	}

	public void handleResponse(RequestContext reqc, ResponseContext resc){
		HttpServletRequest req = (HttpServletRequest) reqc.getRequest();
		HttpServletResponse resp = (HttpServletResponse) resc.getResponse();

		req.setAttribute("result", resc.getResult());

		RequestDispatcher dispatcher = req.getRequestDispatcher(resc.getTarget());
		try {
			dispatcher.forward(req, resp);
		}
		catch(ServletException ex){}
		catch(IOException ex){}
	}
}
