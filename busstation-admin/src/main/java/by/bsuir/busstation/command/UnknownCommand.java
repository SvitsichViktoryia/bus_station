package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class UnknownCommand implements Command {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		return PagesUtil.VIEW_404;
	}

}
