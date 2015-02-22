package com.thawtspot.rp.nodeapp.startup;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ErrorHandler;


public class JettyErrorHandler extends ErrorHandler {

	@Override
	public void handle(String str, Request req, HttpServletRequest httpReq, HttpServletResponse resp) throws IOException {
		
		Writer w = null;
		
		try {
			resp.setContentType("application/json");
			w = resp.getWriter();
			w.write("{\"STATUS\": \"DEFAULT ERROR\"}");
			System.out.println("Reached JettyErrorHandler - need better error handling in servlet. Request Path = "+req.getContextPath());
			System.out.println("Message:"+str);
		} 
		catch (Exception e) {
			if(w != null) w.close();
			e.printStackTrace();
		}
		
	}
}

