package com.ck.ck181228.init.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.ck.ck181228.init.CacheKit;

/**
 * Servlet Filter implementation class FilterUtil
 */
@WebFilter(urlPatterns = { "/jump/*","/roleController","/privilegeController","/MenuController" })
public class FilterUtil implements Filter {

	/**
	 * Default constructor.
	 */
	public FilterUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest hsr = (HttpServletRequest) request;
//		Object username = hsr.getSession().getAttribute("user");
		CacheKit cac = new CacheKit();
		if (cac.getByCache(hsr) == null) {
			request.getRequestDispatcher("/jump/login.do").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
