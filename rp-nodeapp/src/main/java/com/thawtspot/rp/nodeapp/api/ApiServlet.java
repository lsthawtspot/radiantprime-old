package com.thawtspot.rp.nodeapp.api;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ApiServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HttpServlet.class);

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.info("Initializing System.");
   	 	// setup services
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setStatus(200);
		resp.getWriter().println("Testing - You hit Post.");
		return;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setStatus(200);
		resp.getWriter().println("Testing - Leo testing.");
		return;
	}
	
	public void destroy() {
		// Make sure to shutdown services
	}
}
