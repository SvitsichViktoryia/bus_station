package by.bsuir.busstation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsuir.busstation.command.CommandEnum;

public class CheckAccessFilter implements Filter {

	private static final String REQUEST_COMMAND = "action";
	private static final String REQUEST_USER_ID = "userId";

	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		CommandEnum command = CommandEnum.valueOf(req.getParameter(REQUEST_COMMAND));
		Long userId = (Long) session.getAttribute(REQUEST_USER_ID);

		if (command.equals(CommandEnum.AUTH)) {
			chain.doFilter(request, response);
		} else {
			if (userId != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect("do?action=AUTH");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
