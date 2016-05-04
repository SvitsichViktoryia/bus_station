package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Role;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class AuthCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String REQUEST_LOGIN = "login";
	private static final String REQUEST_PASSWORD = "password";
	private static final String REQUEST_USER_ID = "userId";
	private static final String REQUEST_ERROR = "message";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private IUserService userService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if (request.getMethod().equals(METHOD_POST)) {
			
			String login = request.getParameter(REQUEST_LOGIN);
			String password = request.getParameter(REQUEST_PASSWORD);
			User user = checkUser(login, password);
			
			if (user != null) {
				request.getSession().setAttribute(REQUEST_USER_ID, user.getId());
				return PagesUtil.REDIRECT_MAIN;
			} else {
				request.setAttribute(REQUEST_ERROR, "неверный логин/пароль!");
				return PagesUtil.VIEW_AUTHORIZATION;
			}
		} else {
			return PagesUtil.VIEW_AUTHORIZATION;
		}
	}

	private User checkUser(String login, String password) throws ServiceException {
		User user = userService.loadUserByUsername(login);
		if (user != null) {
			Role role = user.getRoles().iterator().next();
			if (user.getPassword().equals(password) && ROLE_USER.equals(role.getName())) {
				return user;
			}
		}
		return null;
	}
}
