package by.bsuir.busstation.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommandFactory {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("controller-context.xml");
	private static final String PARAM_COMMAND = "action";

	public static Command getCommand(HttpServletRequest request) {
		Command command = null;
		CommandEnum commandEnum = null;

		try {
			commandEnum = CommandEnum.valueOf(request.getParameter(PARAM_COMMAND));
		} catch (IllegalArgumentException ex) {
			commandEnum = CommandEnum.UNKNOWN;
		}

		switch (commandEnum) {
		case AUTH:
			command = context.getBean(AuthCommand.class);
			break;
		case REGISTRATION:
			command = context.getBean(RegistrationCommand.class);
			break;
		case MAIN:
			command = context.getBean(MainCommand.class);
			break;
		case UNKNOWN:
			command = context.getBean(UnknownCommand.class);
			break;
		case TICKETS:
			command = context.getBean(TicketsCommand.class);
			break;
		case LOGOUT:
			command = context.getBean(LogoutCommand.class);
			break;
		}
		return command;
	}
}
