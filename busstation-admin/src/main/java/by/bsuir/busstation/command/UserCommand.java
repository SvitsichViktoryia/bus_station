package by.bsuir.busstation.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.busstation.entity.Role;
import by.bsuir.busstation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class UserCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String LOGIN_PARAM = "login";
	private static final String ID_PARAM = "id";
	private static final String EMAIL_PARAM = "email";
	private static final String PASSWORD_PARAM = "password";
	private static final String USERS_PARAM = "users";
	private static final String MESSAGE_PARAM = "message";
	private static final String ADMIN_PARAM = "admin";
	private static final String ADMIN_USER_PARAM = "admin_user";

	private static final String USER_PARAM = "user";
	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	@Autowired
	public IUserService userService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		if (request.getMethod().equals(METHOD_POST)) {
			if (request.getParameter(USER_PARAM) != null) {
				return deleteUser(request);}
			if (request.getParameter(ADMIN_PARAM) != null) {
				return Admin(request);}
			if (request.getParameter(ADMIN_USER_PARAM) != null) {
				return AdminUser(request);}

			if (!Valid(request.getParameter(LOGIN_PARAM),request.getParameter(EMAIL_PARAM))) {
				List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
				request.setAttribute(USERS_PARAM, users);
				request.setAttribute(MESSAGE_PARAM, "проверьте введённые данные.");
				return PagesUtil.VIEW_USERS;
			}

			String id = request.getParameter(ID_PARAM);
			String login = request.getParameter(LOGIN_PARAM);
			String email = request.getParameter(EMAIL_PARAM);

			if (id != null) {
				return updateUser(Long.valueOf(id), login, email);
			} else {
				return createUser(login, email);
			}

		} else {
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			return PagesUtil.VIEW_USERS;
		}
	}

	public boolean Valid(String login, String email) {
		Pattern p = Pattern.compile("^[A-Za-z0-9_]{3,15}$");
		Matcher m = p.matcher(login);
		if (!m.matches()){
			return false;
		}
		p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
		m = p.matcher(email);
		if (!m.matches()){
			return false;
		}
		return true;
	}

    public int CountAdmin (String ROLE, IUserService ususer )throws ServiceException{
		Role  rolel;
		int i=0;
		List<User> userss = new ArrayList<>(new HashSet<>(ususer.readAll()));
		for (User item: userss) {
			rolel = item.getRoles().iterator().next();
			if(ROLE.equals(rolel.getName())){
				i++;}
		}
		return i;
	}

	public String deleteUser(HttpServletRequest request) throws ServiceException{
		Long userId = Long.valueOf(request.getParameter(USER_PARAM));
		User userr = userService.read(userId);
		Role  rolel = userr.getRoles().iterator().next();
		if (ROLE_ADMIN.equals(rolel.getName())){
		int i=0;
		List<User> userss = new ArrayList<>(new HashSet<>(userService.readAll()));
		for (User item: userss) {
			 rolel = item.getRoles().iterator().next();
			if(ROLE_ADMIN.equals(rolel.getName())){
				i++;}
		}
		if(i==1){
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			request.setAttribute(MESSAGE_PARAM, "Невозможно изменить. Остался один админ." );
			return PagesUtil.VIEW_USERS;}}

		try {
			User user = userService.read(userId);
			userService.delete(userId);

		} catch (Exception e) {
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			request.setAttribute(MESSAGE_PARAM, "Невозможно удалить пользователя");
			return PagesUtil.VIEW_USERS;
		}
		return PagesUtil.REDIRECT_USERS;
	}

	public String Admin(HttpServletRequest request) throws ServiceException{
		Long userId = Long.valueOf(request.getParameter(ADMIN_PARAM));

		try {
			User user1 = userService.read(userId);
			User user = userService.loadUserByUsername(user1.getLogin());
			checkUser(user,ROLE_USER);
			if (user != null) {

					Role role = user.getRoles().iterator().next();
					role.setName("ROLE_ADMIN");
					userService.update(user);

				List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
				request.setAttribute(USERS_PARAM, users);
				request.setAttribute(MESSAGE_PARAM, "Админ создан");
				return PagesUtil.VIEW_USERS;
			} else {
				List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
				request.setAttribute(USERS_PARAM, users);
				request.setAttribute(MESSAGE_PARAM, "Админ");
				return PagesUtil.VIEW_USERS;
			}
		} catch (Exception e) {
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			request.setAttribute(MESSAGE_PARAM, "Невозможно создать админа");
			return PagesUtil.VIEW_USERS;
		}

	}

	public User checkUser(User user,String ROLE_USER) {
		if (user != null) {
			Role role = user.getRoles().iterator().next();
			if (ROLE_USER.equals(role.getName())) {
				return user;
			}
		}
		return null;
	}

	public String AdminUser(HttpServletRequest request) throws ServiceException{ // Админ--пользователь
		Long userId = Long.valueOf(request.getParameter(ADMIN_USER_PARAM));
		int i=0;
		List<User> userss = new ArrayList<>(new HashSet<>(userService.readAll()));
		for (User item: userss) {
			Role rolel = item.getRoles().iterator().next();
			if(ROLE_ADMIN.equals(rolel.getName())){
				i++;}}
	if(i==1){
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			request.setAttribute(MESSAGE_PARAM, "Невозможно изменить. Остался один админ." );
			return PagesUtil.VIEW_USERS;}

		try {
			User user1 = userService.read(userId);
			User user = userService.loadUserByUsername(user1.getLogin());
			checkAdmin(user,ROLE_ADMIN);
			if (user != null) {
				Role role = user.getRoles().iterator().next();
				role.setName("ROLE_USER");
				userService.update(user);

				List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
				request.setAttribute(USERS_PARAM, users);
				request.setAttribute(MESSAGE_PARAM, "Пользователь создан");
				return PagesUtil.VIEW_USERS;
			} else {
				List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
				request.setAttribute(USERS_PARAM, users);
				request.setAttribute(MESSAGE_PARAM, "Пользователь");
				return PagesUtil.VIEW_USERS;
			}
		} catch (Exception e) {
			List<User> users = new ArrayList<>(new HashSet<>(userService.readAll()));
			request.setAttribute(USERS_PARAM, users);
			request.setAttribute(MESSAGE_PARAM, "Невозможно создать пользователя");
			return PagesUtil.VIEW_USERS;
		}

	}

	public User checkAdmin(User user, String ROLE_ADMIN) {
		if (user != null) {
			Role role = user.getRoles().iterator().next();
			if (ROLE_ADMIN.equals(role.getName())) {
				return user;
			}
		}
		return null;
	}

	public String createUser(String login, String email) throws ServiceException {
		User user = new User();
		user.setLogin(login);
		user.setEmail(email);
		userService.create(user);
		return PagesUtil.REDIRECT_USERS;
	}

	public String updateUser(Long id, String login, String email) throws ServiceException {
		User user = userService.read(id);
		user.setLogin(login);
		user.setEmail(email);
		userService.update(user);
		return PagesUtil.REDIRECT_USERS;
	}
}
