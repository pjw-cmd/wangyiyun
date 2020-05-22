package com.zjgsu.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * If there if error in uploading a file, return a picture.
 * 
 */
public class ErrorImgFilter extends HttpServlet implements Filter {

	private static final Logger logger = Logger.getLogger(com.zjgsu.filter.ErrorImgFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String realPath = req.getSession().getServletContext().getRealPath("/");
		String contextPath = req.getContextPath();
		String requestURI = req.getRequestURI();

		String fileUrl = realPath + requestURI.substring(contextPath.length());

		File f = new File(fileUrl);
		if (!f.exists()) {
			request.getRequestDispatcher("/error/notfound.png").forward(request, response);
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
