package by.bsuir.busstation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.bsuir.busstation.command.Command;
import by.bsuir.busstation.command.CommandFactory;
import by.bsuir.busstation.service.ServiceException;

public class WebController extends HttpServlet {

	private static final long serialVersionUID = -1562424965010770508L;
	private static final Logger LOG = LoggerFactory.getLogger(WebController.class);
	private static final String FORWARD = "forward";

	public WebController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Command command = CommandFactory.getCommand(request);
			String forward = command.service(request, response);
			request.setAttribute(FORWARD, forward);
			
		} catch (ServiceException ex) {
			LOG.error(ex.getMessage());
		}
	}

}
