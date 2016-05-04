package by.bsuir.busstation.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.entity.Ticket;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.IRouteService;
import by.bsuir.busstation.service.ITicketService;
import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class MainCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String ROUTE_PARAM = "route";
	private static final String REQUEST_USER_ID = "userId";
	private static final String ROUTES_PARAM = "routes";
	private static final String MESSAGE = "message";

	@Autowired
	private IRouteService routeService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ITicketService ticketService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		if (request.getMethod().equals(METHOD_POST)) {
			HttpSession session = request.getSession();
			
			Long routeId = Long.valueOf(request.getParameter(ROUTE_PARAM));
			Long userId = (Long) session.getAttribute(REQUEST_USER_ID);
			
			Route route = routeService.read(routeId);
			User user = userService.read(userId);

			if (checkOrder(route, user) && checkSeatsCount(route)) {
				Ticket ticket = new Ticket();
				ticket.setRoute(route);
				ticket.setUser(user);
				ticket.setStatus(false);
				
				route.getTickets().add(ticket);
				ticketService.create(ticket);
				return PagesUtil.REDIRECT_MAIN;
				
			} else {
				List<Route> routes = new ArrayList<>(new HashSet<>(routeService.readAll()));
				if (!checkOrder(route, user)) {
					request.setAttribute(MESSAGE, "Вы уже заказали билет на данный рейс.");
				} else {
					request.setAttribute(MESSAGE, "Все билеты распроданы.");
				}
				request.setAttribute(ROUTES_PARAM, routes);
				return PagesUtil.VIEW_MAIN;
	
			}
		} else {
			List<Route> routes = new ArrayList<>(new HashSet<>(routeService.readAll()));
			request.setAttribute(ROUTES_PARAM, routes);
			return PagesUtil.VIEW_MAIN;
		}
	}

	private boolean checkOrder(Route route, User user) {
		List<Ticket> tickets = new ArrayList<>();
		tickets.addAll(user.getTickets());

	//	for (Ticket ticket : tickets) {
	//		if (ticket.getRoute().equals(route)) {
	//			return false;
	//		}
	//	}
		return true;
	}
	
	private boolean checkSeatsCount(Route route) {
		return route.getBus().getSeats() > route.getTickets().size();
	}
}
