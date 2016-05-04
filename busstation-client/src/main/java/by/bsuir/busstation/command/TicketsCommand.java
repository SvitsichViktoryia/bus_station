package by.bsuir.busstation.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Ticket;
import by.bsuir.busstation.service.ITicketService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class TicketsCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String TICKET_PARAM = "ticket";
	private static final String REQUEST_USER_ID = "userId";
	private static final String TICKETS_PARAM = "tickets";

	@Autowired
	private ITicketService ticketService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if (request.getMethod().equals(METHOD_POST)) {
			Long ticketId = Long.valueOf(request.getParameter(TICKET_PARAM));
			Ticket ticket = ticketService.read(ticketId);
			ticket.getUser().getTickets().remove(ticket);
			ticket.getRoute().getTickets().remove(ticket);
			ticketService.delete(ticketId);
			return PagesUtil.REDIRECT_TICKETS;

		} else {
			HttpSession session = request.getSession();
			Long userId = (Long) session.getAttribute(REQUEST_USER_ID);

			List<Ticket> tickets = ticketService.readByUser(userId);
			request.setAttribute(TICKETS_PARAM, tickets);
			return PagesUtil.VIEW_TICKETS;
		}
	}

}
