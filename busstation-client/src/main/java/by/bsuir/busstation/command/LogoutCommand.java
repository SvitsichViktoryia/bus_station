package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class LogoutCommand implements Command {

	private static final String USER_ID_PARAM = "userId";

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		HttpSession session = request.getSession();
		session.removeAttribute(USER_ID_PARAM);
		return PagesUtil.REDIRECT_AUTH;
	}

}
