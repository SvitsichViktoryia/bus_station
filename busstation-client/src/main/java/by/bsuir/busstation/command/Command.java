package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.bsuir.busstation.service.ServiceException;

public interface Command {
	Logger LOG = LoggerFactory.getLogger(Command.class);
	String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
