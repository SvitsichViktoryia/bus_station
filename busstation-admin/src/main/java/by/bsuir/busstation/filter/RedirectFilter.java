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

public class RedirectFilter implements Filter {

	private static final String FORWARD = "forward";
	private static final String REDIRECT_PREFIX = "redirect:";

	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
		String forward = (String) request.getAttribute(FORWARD);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (forward.startsWith(REDIRECT_PREFIX)) {
			forward = forward.replace(REDIRECT_PREFIX, "");
			resp.sendRedirect(forward);
		} else {
			if (!forward.equals(""))
				req.getRequestDispatcher(forward).forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
