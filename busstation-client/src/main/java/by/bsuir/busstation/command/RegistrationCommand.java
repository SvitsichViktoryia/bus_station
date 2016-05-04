package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Role;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class RegistrationCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String REQUEST_LOGIN = "login";
	private static final String REQUEST_PASSWORD = "password";
	private static final String REQUEST_CONFIRM_PASSWORD = "confirm";
	private static final String REQUEST_EMAIL = "email";
	private static final String REQUEST_ERROR = "message";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private IUserService userService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if (request.getMethod().equals(METHOD_POST)) {
			String login = request.getParameter(REQUEST_LOGIN);
			String password = request.getParameter(REQUEST_PASSWORD);
			String confirm = request.getParameter(REQUEST_CONFIRM_PASSWORD);
			String email = request.getParameter(REQUEST_EMAIL);

			Pattern p = Pattern.compile("^[A-Za-z0-9_]{3,15}$");
			Matcher m = p.matcher(login);
			if (!m.matches()){
				request.setAttribute(REQUEST_ERROR, "проверьте введённые данные.");
				return PagesUtil.VIEW_REGISTRATION;
			}
			p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
			m = p.matcher(email);
			if (!m.matches()){
				request.setAttribute(REQUEST_ERROR, "проверьте введённые данные.");
				return PagesUtil.VIEW_REGISTRATION;
			}
			p = Pattern.compile("^[A-Za-z0-9_]{3,15}$");
			m = p.matcher(password);
			if (!m.matches()){
				request.setAttribute(REQUEST_ERROR, "проверьте введённые данные.");
				return PagesUtil.VIEW_REGISTRATION;
			}
			p = Pattern.compile("^[A-Za-z0-9_]{3,15}$");
			m = p.matcher(confirm);
			if (!m.matches()){
				request.setAttribute(REQUEST_ERROR, "проверьте введённые данные.");
				return PagesUtil.VIEW_REGISTRATION;
			}

			if (checkUser(login, password, confirm)) {
				Role role = new Role();
				role.setName(ROLE_USER);

				User user = new User();
				user.setEmail(email);
				user.setLogin(login);
				user.setPassword(password);
				user.getRoles().add(role);
				role.setUser(user);

				userService.create(user);
				return PagesUtil.REDIRECT_AUTH;

			} else {
				request.setAttribute(REQUEST_ERROR, "проверьте введённые данные.");
				return PagesUtil.VIEW_REGISTRATION;
			}
		} else {
			return PagesUtil.VIEW_REGISTRATION;
		}
	}

	private boolean checkUser(String login, String password, String confirm) throws ServiceException {
		User user = userService.loadUserByUsername(login);
		if (user == null) {
			if (password.equals(confirm)) {
				return true;
			}
		}
		return false;
	}

}
